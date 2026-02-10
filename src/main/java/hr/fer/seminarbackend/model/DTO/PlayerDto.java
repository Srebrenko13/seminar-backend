package hr.fer.seminarbackend.model.DTO;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {
    private Long playerId;
    private String username;
}