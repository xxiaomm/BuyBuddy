package com.example.orderservice.dto;

import com.example.orderservice.enums.PaymentMethod;
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
public class PaymentRequest {
    UUID orderId;
    double totalPrice;

    PaymentMethod paymentMethod;
    private String message;



}
