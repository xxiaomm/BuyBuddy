package com.example.accountservice.repository;

import com.example.accountservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
