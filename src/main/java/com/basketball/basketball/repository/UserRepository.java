package com.basketball.basketball.repository;

import com.basketball.basketball.model.LoginUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<LoginUser, Long> {
    Optional<LoginUser> findOneByUsername(String username);
}
