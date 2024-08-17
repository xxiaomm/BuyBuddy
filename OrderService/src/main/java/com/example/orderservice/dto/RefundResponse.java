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
public class RefundResponse {
//    private UUID refundId;
    boolean isRefunded;
    private UUID orderId;
    private double totalPrice;
    String paymentStatus;
    private String message;
}
