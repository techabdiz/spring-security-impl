package com.deaspider.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deaspider.models.Role;

public interface RoleRepo extends JpaRepository<Role, Long>{
    Optional<Role> findByName(String role);
    boolean existsByName(String role);
}
