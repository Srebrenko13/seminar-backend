package hr.fer.seminarbackend.handler;

import hr.fer.seminarbackend.model.ClientMessage;
import org.springframework.web.socket.WebSocketSession;

public interface MessageHandler {
    ClientMessage.MessageType supports();
    void handle(WebSocketSession session, ClientMessage message) throws Exception;
}
