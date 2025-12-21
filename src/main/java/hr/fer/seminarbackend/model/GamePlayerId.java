package hr.fer.seminarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GamePlayerId implements Serializable {

    @Column(name = "game_id")
    private Long gameId;

    @Column(name = "player_id")
    private Long playerId;
}

