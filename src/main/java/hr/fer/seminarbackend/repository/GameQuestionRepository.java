package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.GameQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameQuestionRepository extends JpaRepository<GameQuestion, Long> {
}
