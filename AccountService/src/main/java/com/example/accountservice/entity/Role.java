package com.example.accountservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @GeneratedValue(strategy = GenerationType.AUTO) // Auto generation strategy
//    @Column(name = "id", updatable = false, nullable = false)

    @Column(length = 60)
    private String name;
}
