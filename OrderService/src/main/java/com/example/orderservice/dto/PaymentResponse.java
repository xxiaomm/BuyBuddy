package com.example.orderservice.dto;

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
public class PaymentResponse {
    private boolean isPaid;
    private UUID orderId;
    double totalPrice;
//    private UUID paymentId;
    private String paymentStatus;
    private String message;


}
