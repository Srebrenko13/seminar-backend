package hr.fer.seminarbackend.model.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AnswerDto {
    private Long answerId;
    private Long questionId;
    private String content;
    private Boolean correct;
}