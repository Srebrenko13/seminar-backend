package hr.fer.seminarbackend.model.DTO;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamePlayerDto {
    private Long gameId;
    private Long playerId;
}
