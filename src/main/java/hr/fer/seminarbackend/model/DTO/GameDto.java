package hr.fer.seminarbackend.model.DTO;

import lombok.*;

import java.time.OffsetDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameDto {
    private Long id;
    private OffsetDateTime createdAt;
}
