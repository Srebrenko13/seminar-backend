package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.GameQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameQuestionRepository extends JpaRepository<GameQuestion, Long>,
        JpaSpecificationExecutor<GameQuestion> {
}
