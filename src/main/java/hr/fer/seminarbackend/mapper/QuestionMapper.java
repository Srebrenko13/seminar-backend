package hr.fer.seminarbackend.mapper;

import hr.fer.seminarbackend.model.DTO.QuestionDto;
import hr.fer.seminarbackend.model.Question;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionDto toQuestionDto(Question question);

    Question toQuestion(QuestionDto questionDto);
}
