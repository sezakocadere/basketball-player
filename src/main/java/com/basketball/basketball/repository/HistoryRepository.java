package com.basketball.basketball.repository;

import com.basketball.basketball.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
