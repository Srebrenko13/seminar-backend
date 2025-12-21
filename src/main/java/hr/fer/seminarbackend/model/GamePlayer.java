package hr.fer.seminarbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "game_player")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GamePlayer {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private GamePlayerId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("gameId")
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("playerId")
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    public GamePlayer(Game game, Player player) {
        this.game = game;
        this.player = player;
        this.id = new GamePlayerId(game.getId(), player.getId());
    }
}

