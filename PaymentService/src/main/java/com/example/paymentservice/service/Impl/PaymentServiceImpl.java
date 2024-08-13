package com.example.paymentservice.service.Impl;

import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Payment submitPayment(Payment payment) {
        return null;
    }

    @Override
    public Optional<Payment> updatePayment(String orderId, Payment payment) {
        return Optional.empty();
    }

    @Override
    public Optional<Payment> refund(String orderId) {
        return null;
    }

    @Override
    public Optional<Payment> searchPayment(String orderId) {
        return Optional.empty();
    }
}
