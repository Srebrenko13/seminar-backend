package hr.fer.seminarbackend.mapper;

import hr.fer.seminarbackend.model.DTO.GamePlayerDto;
import hr.fer.seminarbackend.model.GamePlayer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GamePlayerMapper {
    GamePlayerDto toGamePlayerDto(GamePlayer gamePlayer);

    GamePlayer toGamePlayer(GamePlayerDto gamePlayerDto);
}
