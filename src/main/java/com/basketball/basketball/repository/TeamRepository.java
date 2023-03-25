package com.basketball.basketball.repository;

import com.basketball.basketball.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
