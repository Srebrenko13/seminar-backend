package hr.fer.seminarbackend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "question")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    @EqualsAndHashCode.Include
    private Long questionId;

    @Column(name = "question", nullable = false, length = 500)
    private String text;

    @Column(name = "duration", nullable = false)
    private Integer duration;
}
