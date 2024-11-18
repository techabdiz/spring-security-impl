package com.deaspider.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deaspider.models.Grants;

public interface GrantsRepo extends JpaRepository<Grants, Long>{
    Optional<Grants> findByName(String grant);
    boolean existsByName(String role);
}
