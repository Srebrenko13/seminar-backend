package hr.fer.seminarbackend.mapper;

import hr.fer.seminarbackend.model.Answer;
import hr.fer.seminarbackend.model.DTO.AnswerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerDto toAnswerDto(Answer answer);

    Answer toAnswer(AnswerDto answerDto);
}
