package hr.fer.seminarbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "game_question",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_game_question_position",
                columnNames = {"game_id", "position"}
        )
)
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GameQuestion {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private GameQuestionId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("gameId")
    @JoinColumn(name = "game_id", nullable = false)
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("questionId")
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "position", nullable = false)
    private int position;

    public GameQuestion(Game game, Question question, int position) {
        this.game = game;
        this.question = question;
        this.position = position;
        this.id = new GameQuestionId(game.getId(), question.getId());
    }
}
