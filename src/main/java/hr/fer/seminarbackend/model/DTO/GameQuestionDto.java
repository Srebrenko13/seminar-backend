package hr.fer.seminarbackend.model.DTO;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameQuestionDto {
    private Long gameId;
    private Long questionId;
    private int position;
}
