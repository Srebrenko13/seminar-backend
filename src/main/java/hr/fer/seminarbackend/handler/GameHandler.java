package hr.fer.seminarbackend.handler;

import hr.fer.seminarbackend.model.ClientMessage;
import hr.fer.seminarbackend.model.WebSocketState;
import hr.fer.seminarbackend.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
public class GameHandler implements MessageHandler {

    private final WebSocketState state;
    private final GameService gameService;

    @Override
    public ClientMessage.MessageType supports() { return ClientMessage.MessageType.ANSWER; }

    @Override
    public void handle(WebSocketSession session, ClientMessage message) throws Exception {
        Long gameId = (Long) session.getAttributes().get("GAME_ID");
        Long playerId = (Long) session.getAttributes().get("PLAYER_ID");

        if (gameId !=  null && playerId != null) {
            Long answerId = Long.parseLong(message.getPayload());
            gameService.submitAnswer(gameId, playerId, answerId);
        }
    }
}
