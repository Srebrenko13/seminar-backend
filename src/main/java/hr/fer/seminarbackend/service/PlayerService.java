package hr.fer.seminarbackend.service;

import hr.fer.seminarbackend.mapper.PlayerMapper;
import hr.fer.seminarbackend.model.DTO.PlayerDto;
import hr.fer.seminarbackend.model.Player;
import hr.fer.seminarbackend.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlayerService {
    private final PlayerRepository repository;
    private final PlayerMapper mapper;

    public PlayerDto getByUsername(String username) {
        Player savedObject = repository.findByUsernameIgnoreCase(username)
                .orElseThrow(() -> new RuntimeException("User doesn't exist!"));

        return mapper.toPlayerDto(savedObject);
    }

    public PlayerDto savePlayer(String username){
        Player player = new Player();
        player.setUsername(username);

        Player savedObject = repository.save(player);
        return mapper.toPlayerDto(savedObject);
    }

    public Player getOrCreatePlayer(String username){
        String sanitizedUsername = username.toLowerCase().trim();

        return repository.findByUsernameIgnoreCase(sanitizedUsername)
                .orElseGet(() -> {
                    Player newPlayer = new Player();
                    newPlayer.setUsername(sanitizedUsername);
                    return repository.save(newPlayer);
                });
    }
}
