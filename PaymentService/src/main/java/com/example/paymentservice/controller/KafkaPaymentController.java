package com.example.paymentservice.controller;

import com.example.orderservice.dto.PaymentRequest;
import com.example.orderservice.dto.RefundRequest;
import com.example.orderservice.service.Impl.OrderServiceImpl;
import com.example.paymentservice.service.ConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Api(value = "payment-controller")
@RestController
@RequestMapping("/payments")
public class KafkaPaymentController {
    @Autowired
    ConsumerService consumerService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @ApiOperation(value = "consumer created order, do the payment REST API")
    @PostMapping("/pay")
    public String doPayment(@RequestBody PaymentRequest paymentRequest) {
        try {
            // 将 PaymentRequest 对象序列化为 JSON 字符串
            String message = objectMapper.writeValueAsString(paymentRequest);

            consumerService.doPayment(message);
            return "dPayment request sent successfully!";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to send payment request";
        }
    }

    @ApiOperation(value = "Get Refund REST API")
    @PostMapping("/refund")
    public String reversePayment(@RequestBody RefundRequest refundRequest) {
        try {
            // 将 PaymentRequest 对象序列化为 JSON 字符串
            String message = objectMapper.writeValueAsString(refundRequest);

            consumerService.reversePayment(message);
            return "Refund request sent successfully!";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to send refund request";
        }
    }

//    @ApiOperation(value = "Update Payment REST API, eg. payment method, billing address")
//    @PutMapping
//    public void updatePayment(@RequestBody PaymentRequest paymentRequest) {
//
//    }
//

//
//    @ApiOperation(value = "Search Payment REST API")
//    @GetMapping
//    public void searchPayment() {
//
//    }


}
