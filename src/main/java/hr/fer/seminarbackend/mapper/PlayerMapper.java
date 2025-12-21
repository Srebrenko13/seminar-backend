package hr.fer.seminarbackend.mapper;

import hr.fer.seminarbackend.model.DTO.PlayerDto;
import hr.fer.seminarbackend.model.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {
    PlayerDto toPlayerDto(Player player);

    Player toPlayer(PlayerDto playerDto);
}
