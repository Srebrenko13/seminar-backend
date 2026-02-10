package hr.fer.seminarbackend.repository;

import hr.fer.seminarbackend.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository  extends JpaRepository<Game, Long>,
        JpaSpecificationExecutor<Game> {
}
