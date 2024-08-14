package com.example.paymentservice.controller;

import com.example.paymentservice.common.PaymentResponse;
import com.example.paymentservice.service.PaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Api(value = "payment-controller")
@RestController
@RequestMapping("payments")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @ApiOperation(value = "Submit Payment REST API")
    @PostMapping
    public ResponseEntity<PaymentResponse> submitPayment() {
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Update Payment REST API, eg. payment method, billing address")
    @PutMapping
    public void updatePayment() {

    }

    @ApiOperation(value = "Get Refund REST API")
    @PostMapping
    public void reversePayment() {

    }

    @ApiOperation(value = "Search Payment REST API")
    @GetMapping
    public void searchPayment() {

    }


}
