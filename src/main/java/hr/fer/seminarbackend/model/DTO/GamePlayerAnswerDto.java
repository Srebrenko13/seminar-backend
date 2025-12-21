package hr.fer.seminarbackend.model.DTO;

import lombok.*;

import java.time.OffsetDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayerAnswerDto {
    private Long gameId;
    private Long playerId;
    private Long questionId;

    private Long answerId;
    private Integer responseMs;
    private int points;
    private Boolean correct;
    private OffsetDateTime answeredAt;
}
