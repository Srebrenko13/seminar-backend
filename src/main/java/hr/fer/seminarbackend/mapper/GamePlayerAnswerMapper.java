package hr.fer.seminarbackend.mapper;

import hr.fer.seminarbackend.model.DTO.GamePlayerAnswerDto;
import hr.fer.seminarbackend.model.GamePlayerAnswer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GamePlayerAnswerMapper {
    GamePlayerAnswerDto toGamePlayerAnswerDto(GamePlayerAnswer gamePlayerAnswer);

    GamePlayerAnswer toGamePlayerAnswer(GamePlayerAnswerDto gamePlayerAnswerDto);
}
