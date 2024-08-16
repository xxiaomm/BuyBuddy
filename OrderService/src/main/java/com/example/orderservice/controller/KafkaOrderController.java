package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.PaymentResponse;
import com.example.orderservice.service.ConsumerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@Api(value = "kafka-order-controller")
@RestController
@RequestMapping("/orders")
public class KafkaOrderController {

    @Autowired
    ConsumerService consumerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @ApiOperation(value = "update Kafka Order Status REST API")
    @PutMapping("/kafka/status")
    public String updateKafkaOrderStatus(@RequestBody PaymentResponse response) {

        try {
            // 将 PaymentRequest 对象序列化为 JSON 字符串
            String message = objectMapper.writeValueAsString(response);

            consumerService.updateKafkaOrderStatus(message);
            return "Update Order status successfully!";
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Failed to Update Order status";
        }
    }
}
