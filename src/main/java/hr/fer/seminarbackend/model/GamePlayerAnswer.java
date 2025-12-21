package hr.fer.seminarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "game_player_answer")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GamePlayerAnswer {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private GamePlayerAnswerId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "game_id", referencedColumnName = "game_id", insertable = false, updatable = false),
            @JoinColumn(name = "player_id", referencedColumnName = "player_id", insertable = false, updatable = false)
    })
    private GamePlayer gamePlayer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "game_id", referencedColumnName = "game_id", insertable = false, updatable = false),
            @JoinColumn(name = "question_id", referencedColumnName = "question_id", insertable = false, updatable = false)
    })
    private GameQuestion gameQuestion;

    @Column(name = "answer_id")
    private Long answerId;

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

    public GamePlayerAnswer(Long gameId, Long playerId, Long questionId) {
        this.id = new GamePlayerAnswerId(gameId, playerId, questionId);
        this.points = 0;
    }

    public void chooseAnswer(Answer answer) {
        if (answer == null) {
            this.answerId = null;
            this.selectedAnswer = null;
            return;
        }
        this.answerId = answer.getId();
        this.selectedAnswer = answer;
    }
}

