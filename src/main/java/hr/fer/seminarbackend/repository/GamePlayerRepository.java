package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.GamePlayerAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GamePlayerRepository extends JpaRepository<GamePlayerAnswer, Long>,
        JpaSpecificationExecutor<GamePlayerAnswer>{
}
