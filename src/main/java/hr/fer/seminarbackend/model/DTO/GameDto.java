package hr.fer.seminarbackend.model.DTO;

import hr.fer.seminarbackend.model.Player;
import lombok.*;

import java.time.OffsetDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long gameId;
    private OffsetDateTime createdAt;
    private Player playerOne;
    private Player playerTwo;
    private Boolean finished;
    private Double scoreOne;
    private Double scoreTwo;
}
