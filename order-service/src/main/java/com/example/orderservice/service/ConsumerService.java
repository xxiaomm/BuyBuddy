package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.PaymentResponse;
import com.example.orderservice.dto.RefundResponse;
import com.example.orderservice.enums.OrderStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Service
public class ConsumerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    OrderService orderService;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger= LoggerFactory.getLogger(ConsumerService.class);


    @KafkaListener(topics = "payment-response-topic", groupId = "paid")
    public String updatePaidStatus(String message) {
        PaymentResponse response = null;
        try {
            response = objectMapper.readValue(message, PaymentResponse.class);
            if (! response.isPaid()) return "Paid Failed! ";
            logger.info("kafka: start update order status as paid..");
            UUID uuid = response.getOrderId();
            orderService.updateOrderStatus(uuid, OrderStatus.PAID);
            logger.info("kafka: finish update order status as paid..");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "kafka: consumer service has updated order status as paid! ";
    }

    @KafkaListener(topics = "refund-response-topic", groupId = "refund")
    public String updateRefundStatus(String message) {
        RefundResponse response = null;
        try {
            response = objectMapper.readValue(message, RefundResponse.class);
            if (! response.isRefunded()) return "Refund Failed! ";
            logger.info("kafka: start update order status as refunded..");
            UUID uuid = response.getOrderId();
            orderService.updateOrderStatus(uuid, OrderStatus.REFUNDED);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "kafka: consumer service has updated order status as refunded! ";
    }

}
