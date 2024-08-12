package com.example.accountservice.repository;

import com.example.accountservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
