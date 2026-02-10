package hr.fer.seminarbackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import hr.fer.seminarbackend.mapper.AnswerMapper;
import hr.fer.seminarbackend.model.*;
import hr.fer.seminarbackend.model.DTO.AnswerDto;
import hr.fer.seminarbackend.repository.AnswerRepository;
import hr.fer.seminarbackend.repository.GameRepository;
import hr.fer.seminarbackend.repository.QuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameRepository gameRepository;
    private final PlayerService playerService;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;
    private final ObjectMapper mapper;

    private final Map<Long, ActiveGameSession> activeGames = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);
    private final Map<Long, ScheduledFuture<?>> activeTimers = new ConcurrentHashMap<>();

    @Transactional
    public ActiveGameSession startGame(String usernameOne, String usernameTwo,
                          WebSocketSession session1, WebSocketSession session2,
                          WebSocketSession screenSession) {
        Player playerOne = playerService.getOrCreatePlayer(usernameOne);
        Player playerTwo = playerService.getOrCreatePlayer(usernameTwo);

        Game newGame = Game.builder().
                playerOne(playerOne).
                playerTwo(playerTwo).
                scoreOne(0.0).
                scoreTwo(0.0).
                createdAt(OffsetDateTime.now()).
                finished(false).
                build();

        Game savedGame = gameRepository.save(newGame);

        ActiveGameSession session = new ActiveGameSession(savedGame, session1, session2, screenSession);

        session.setQuestions(questionRepository.findRandomQuestions(10));

        activeGames.put(savedGame.getGameId(),  session);
        return session;
    }

    public void startNextQuestion(Long gameId){
        ActiveGameSession session = activeGames.get(gameId);
        Question question = session.getCurrentQuestion();
        if (question == null) return;

        List<Answer> answers = new ArrayList<>(answerRepository.findAllByQuestion(question));
        Collections.shuffle(answers);

        List<AnswerDto> answersDto = answers.stream()
                .map(answer -> {
                    AnswerDto answerDto = answerMapper.toAnswerDto(answer);
                    answerDto.setCorrect(null);
                    return answerDto;
                }).collect(Collectors.toList());

        long activationTime = System.currentTimeMillis() + 5000;
        session.setQuestionStartTime(activationTime);
        session.setAcceptingAnswers(true);
        session.getPlayersWhoAnswered().clear();

        var roundDto = Map.of(
                "type", "QUESTION",
                "question", question.getText(),
                "answers", answersDto,
                "duration", question.getDuration(),
                "activationTime", activationTime,
                "index", session.getCurrentQuestionIndex() + 1
        );

        broadcastToGame(gameId, roundDto);

        long totalWaitTime = 5 + question.getDuration();
        ScheduledFuture<?> timeoutTask = scheduler.schedule(() -> {
            forceRoundEnd(gameId);
        }, totalWaitTime, TimeUnit.SECONDS);

        activeTimers.put(gameId, timeoutTask);
    }

    public void submitAnswer(Long gameId, Long playerId, Long answerId) {
        ActiveGameSession session = activeGames.get(gameId);
        if (session == null || !session.isAcceptingAnswers()) return;

        long now = System.currentTimeMillis();
        long reactionTime = now - session.getQuestionStartTime();

        if (reactionTime < 0) return; // Cheating check

        if (session.getPlayersWhoAnswered().add(playerId)) {
            Answer answer = answerRepository.findById(answerId).orElse(null);
            if (answer == null) return;

            Question currentQuestion = session.getCurrentQuestion();

            boolean correct = answer.isCorrect() &&
                    answer.getQuestion().getQuestionId().equals(currentQuestion.getQuestionId());

            if (correct) {
                double maxPoints = 1000.0;
                double durationMs = session.getCurrentQuestion().getDuration() * 1000.0;
                double points = maxPoints * (1 - ((reactionTime / durationMs) / 2));
                session.addScore(playerId, points);
            }

            if (session.getPlayersWhoAnswered().size() == 2) {
                cancelTimer(gameId);
                endRound(session);
            }
        }
    }

    private void forceRoundEnd(Long gameId) {
        ActiveGameSession session = activeGames.get(gameId);
        if (session != null && session.isAcceptingAnswers()) {
            endRound(session);
        }
    }

    private void endRound(ActiveGameSession session) {
        session.setAcceptingAnswers(false);

        Question currentQuestion = session.getCurrentQuestion();

        Long correctAnswerId = answerRepository.findByQuestionAndCorrectTrue(currentQuestion)
                .map(Answer::getAnswerId)
                .orElse(-1L);

        var results = Map.of(
                "type", "ROUND_RESULTS",
                "scoreOne", session.getScoreOne(),
                "scoreTwo", session.getScoreTwo(),
                "correctAnswerId", correctAnswerId
        );
        broadcastToGame(session.getGameId(), results);

        session.setCurrentQuestionIndex(session.getCurrentQuestionIndex() + 1);

        scheduler.schedule(() -> {
            if (session.getCurrentQuestionIndex() < session.getQuestions().size()) {
                startNextQuestion(session.getGameId());
            } else {
                finishAndSaveGame(session.getGameId());
                broadcastToGame(session.getGameId(), Map.of("type", "GAME_END"));
            }
        }, 5, TimeUnit.SECONDS);
    }

    private void cancelTimer(Long gameId) {
        ScheduledFuture<?> timer = activeTimers.remove(gameId);
        if (timer != null) timer.cancel(false);
    }

    public void broadcastToGame(Long gameId, Object payload){
        ActiveGameSession session = activeGames.get(gameId);
        if (session == null) return;
        try {
            TextMessage message = new TextMessage(mapper.writeValueAsString(payload));
            if (session.getPlayerOneSession().isOpen()) session.getPlayerOneSession().sendMessage(message);
            if (session.getPlayerTwoSession().isOpen()) session.getPlayerTwoSession().sendMessage(message);
            if (session.getScreenSession().isOpen()) session.getScreenSession().sendMessage(message);
        } catch (Exception e){
            System.err.println("Failed to broadcast to game " + gameId);
        }
    }

    @Transactional
    public void finishAndSaveGame(Long gameId) {
        ActiveGameSession session = activeGames.remove(gameId);

        if (session == null) throw new RuntimeException("Game session not found!");

        Game game = gameRepository.findById(gameId).
                orElseThrow(() -> new EntityNotFoundException("Game record not found!"));

        game.setScoreOne(session.getScoreOne());
        game.setScoreTwo(session.getScoreTwo());
        game.setFinished(true);

        gameRepository.save(game);
    }
}
