package hr.fer.seminarbackend.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.OffsetDateTime;

@Entity
@Table(name = "game")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "game_id")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_one", nullable = false)
    private Player playerOne;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_two", nullable = false)
    private Player playerTwo;

    @Column(name = "finished", nullable = false)
    private boolean finished = false;

    @Column(name = "score_one", nullable = false)
    @Builder.Default
    private Double scoreOne = 0.0;

    @Column(name = "score_two")
    private Double scoreTwo;
}
