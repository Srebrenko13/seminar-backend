package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
