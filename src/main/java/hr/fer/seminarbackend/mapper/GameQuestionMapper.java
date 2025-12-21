package hr.fer.seminarbackend.mapper;

import hr.fer.seminarbackend.model.DTO.GameQuestionDto;
import hr.fer.seminarbackend.model.GameQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameQuestionMapper {
    GameQuestionDto toGameQuestionDto(GameQuestion gameQuestion);

    GameQuestion toGameQuestion(GameQuestionDto gameQuestionDto);
}
