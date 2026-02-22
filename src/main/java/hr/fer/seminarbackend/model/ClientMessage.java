package hr.fer.seminarbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ClientMessage {
    public enum Role { SCREEN, PLAYER }
    public enum MessageType { CONNECT, LOBBY, ANSWER, NEXT_QUESTION }

    Role role;
    MessageType messageType;
    String payload;
}
