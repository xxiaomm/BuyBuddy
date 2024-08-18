package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.PaymentResponse;
import com.example.orderservice.dto.RefundResponse;
import com.example.orderservice.service.ConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Api(value = "kafka-order-controller")
@RestController
@RequestMapping("/orders")
public class KafkaOrderController {

    @Autowired
    ConsumerService consumerService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(KafkaOrderController.class);


    @ApiOperation(value = "update Kafka Order Status REST API")
    @PutMapping("/paid_status")
    public String updatePaidStatus(@RequestBody PaymentResponse response) {
        try {
            // 将 PaymentRequest 对象序列化为 JSON 字符串
            String message = objectMapper.writeValueAsString(response);

            logger.info(consumerService.updatePaidStatus(message));
            return "Update Order status successfully!";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to Update Order status";
        }
    }

    @PutMapping("/refunded_status")
    public String updateRefundedStatus(@RequestBody RefundResponse response) {

        try {
            // 将 PaymentRequest 对象序列化为 JSON 字符串
            String message = objectMapper.writeValueAsString(response);

            consumerService.updateRefundStatus(message);
            return "Update Order status successfully!";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to Update Order status";
        }
    }


}
