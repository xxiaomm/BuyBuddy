package com.example.paymentservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Transactions")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentStatus;

//    private LocalDateTime createdAt;
//
//    private LocalDateTime updatedAt;


}
