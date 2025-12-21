package hr.fer.seminarbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "answer",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_answer_question_answer",
                columnNames = {"question_id", "answer_id"}
        )
)
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @Column(name = "content", nullable = false, length = 500)
    private String content;

    @Column(name = "is_correct", nullable = false)
    private boolean correct;
}

