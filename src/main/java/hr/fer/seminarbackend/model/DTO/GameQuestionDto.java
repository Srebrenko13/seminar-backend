package hr.fer.seminarbackend.model.DTO;

import hr.fer.seminarbackend.model.GameQuestionId;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameQuestionDto {
    private GameQuestionId gameQuestionId;
    private GameDto game;
    private QuestionDto question;
    private int position;
}
