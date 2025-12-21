package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.GamePlayerAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamePlayerRepository extends JpaRepository<GamePlayerAnswer, Long> {
}
