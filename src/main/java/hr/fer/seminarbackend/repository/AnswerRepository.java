package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.Answer;
import hr.fer.seminarbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<Answer> {
    List<Answer> findAllByQuestion(Question question);
    Optional<Answer> findByQuestionAndCorrectTrue(Question question);
}
