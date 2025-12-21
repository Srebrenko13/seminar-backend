package hr.fer.seminarbackend.model.DTO;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    private Long id;
    private Long questionId;
    private String content;
    private boolean correct;
}