package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository  extends JpaRepository<Game, Long> {
}
