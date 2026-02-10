package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>,
        JpaSpecificationExecutor<Question> {

    @Query(nativeQuery = true, value = """
        SELECT * FROM question 
        ORDER BY RANDOM() 
        LIMIT :limit
    """)
    List<Question> findRandomQuestions(@Param("limit")  int limit);
}
