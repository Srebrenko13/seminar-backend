package hr.fer.seminarbackend.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "game_player_answer")
@Getter @Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GamePlayerAnswer {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private GamePlayerAnswerId gamePlayerAnswerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playerId")
    @JoinColumn(name = "player_id", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "game_id", referencedColumnName = "game_id", insertable = false, updatable = false),
            @JoinColumn(name = "question_id", referencedColumnName = "question_id", insertable = false, updatable = false)
    })
    private GameQuestion gameQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "question_id", referencedColumnName = "question_id", insertable = false, updatable = false),
            @JoinColumn(name = "answer_id", referencedColumnName = "answer_id", insertable = false, updatable = false)
    })
    private Answer selectedAnswer;

    @Column(name = "response_ms")
    private Integer responseMs;

    @Column(name = "points", nullable = false)
    private int points = 0;

    @Column(name = "is_correct")
    private Boolean correct;

    @Column(name = "answered_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime answeredAt;

    public GamePlayerAnswer(Game game, Player player, Question question) {
        this.game = game;
        this.player = player;
        this.gamePlayerAnswerId = new GamePlayerAnswerId(game.getGameId(), player.getPlayerId(), question.getQuestionId());
    }
}