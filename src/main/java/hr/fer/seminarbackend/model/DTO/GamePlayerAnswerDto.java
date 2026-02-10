package hr.fer.seminarbackend.model.DTO;

import hr.fer.seminarbackend.model.GamePlayerAnswerId;
import lombok.*;

import java.time.OffsetDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
// ovo treba jos prepravit, mozda ovaj DTO niti ne treba pa ga mozemo maknut
public class GamePlayerAnswerDto {
    private GamePlayerAnswerId gamePlayerAnswerId;
    private Boolean finished;
    private Double scoreOne;
    private Long gameId;
    private Long playerId;
    private Long questionId;

    private AnswerDto answer;
    private Integer responseMs;
    private int points;
    private Boolean correct;
    private OffsetDateTime answeredAt;
}
