package com.basketball.basketball.repository;

import com.basketball.basketball.enums.Status;
import com.basketball.basketball.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    Optional<Player> findByIdAndStatus(Long id, Status status);

    int countByTeamId(Long teamId);
}
