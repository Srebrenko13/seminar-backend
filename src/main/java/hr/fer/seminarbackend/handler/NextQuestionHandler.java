package hr.fer.seminarbackend.handler;

import hr.fer.seminarbackend.model.ClientMessage;
import hr.fer.seminarbackend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
public class NextQuestionHandler implements MessageHandler {

    private final GameService gameService;

    @Override
    public ClientMessage.MessageType supports() {
        return ClientMessage.MessageType.NEXT_QUESTION;
    }

    @Override
    public void handle(WebSocketSession session, ClientMessage message) throws Exception {
        Long gameId = (Long) session.getAttributes().get("GAME_ID");
        if (gameId != null) {
            gameService.handleNextQuestionRequest(gameId);
        }
    }
}
