package com.example.paymentservice.service;

import com.example.paymentservice.entity.Payment;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Service
public interface PaymentService {
    Payment submitPayment(Payment payment);
    Optional<Payment> updatePayment(String orderId, Payment payment);

//    Long doPayment(String orderId); // paymentId

    Optional<Payment> reversePayment(String orderId);
    Optional<Payment> searchPayment(String orderId);
}
