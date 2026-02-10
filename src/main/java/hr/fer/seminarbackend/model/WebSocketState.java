package hr.fer.seminarbackend.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class WebSocketState {
    private final Object lock = new Object();

    private Lobby lobby;
}
