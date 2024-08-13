package com.example.paymentservice.controller;

import com.example.paymentservice.common.PaymentResponse;
import com.example.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@RestController
@RequestMapping("payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> submitPayment() {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public void updatePayment() {

    }

    @PostMapping
    public void refund() {

    }

    @GetMapping
    public void searchPayment() {

    }


}
