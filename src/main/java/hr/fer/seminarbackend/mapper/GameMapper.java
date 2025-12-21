package hr.fer.seminarbackend.mapper;

import hr.fer.seminarbackend.model.DTO.GameDto;
import hr.fer.seminarbackend.model.Game;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameDto toGameDto(Game game);

    Game toGame(GameDto gameDto);
}
