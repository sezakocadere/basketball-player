package com.basketball.basketball.repository;

import com.basketball.basketball.enums.Position;
import com.basketball.basketball.enums.Status;
import com.basketball.basketball.model.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByIdAndStatus(Long id, Status status);

    int countByTeamId(Long teamId);

    Page<Player> findAllByStatus(Status status, Pageable pageable);

    boolean existsByPositionAndTeamIdAndStatus(Position position, Long teamId, Status status);
}
