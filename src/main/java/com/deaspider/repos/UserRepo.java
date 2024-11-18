package com.deaspider.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deaspider.models.User;

public interface UserRepo extends JpaRepository<User, Long>{

    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
}
