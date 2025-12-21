package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
