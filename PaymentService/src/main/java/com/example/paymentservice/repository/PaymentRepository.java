package com.example.paymentservice.repository;

import com.example.paymentservice.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
