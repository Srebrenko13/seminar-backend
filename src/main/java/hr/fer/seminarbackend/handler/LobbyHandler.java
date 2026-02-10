package hr.fer.seminarbackend.handler;

import hr.fer.seminarbackend.model.ClientMessage;
import hr.fer.seminarbackend.model.WebSocketState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

@Component
@RequiredArgsConstructor
public class LobbyHandler implements MessageHandler {

    private final WebSocketState state;

    @Override
    public ClientMessage.MessageType supports() {
        return ClientMessage.MessageType.LOBBY;
    }

    @Override
    public void handle(WebSocketSession session, ClientMessage message) throws Exception {

    }
}
