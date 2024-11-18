package com.deaspider.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deaspider.models.Token;

public interface TokenRepo extends JpaRepository<Token, Long>{
    Optional<Token> findByToken(String token);
}
