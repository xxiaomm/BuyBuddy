package com.example.paymentservice.service;

import com.example.orderservice.dto.PaymentRequest;
import com.example.orderservice.dto.PaymentResponse;
import com.example.orderservice.dto.RefundRequest;
import com.example.orderservice.dto.RefundResponse;
import com.example.paymentservice.enums.PaymentStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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
    private ProducerService producerService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final Logger logger= LoggerFactory.getLogger(ConsumerService.class);

    @KafkaListener(topics = "payment-request-topic", groupId = "orderPay")
    public void doPayment(String message) {
        logger.info("kafka received message payment request is: ");
        // 将 JSON 字符串反序列化为 PaymentRequest 对象
        PaymentRequest request = null;
        try {
            request = objectMapper.readValue(message, PaymentRequest.class);
            logger.info(request.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        logger.info("start paying the order...");
        UUID orderId = request.getOrderId();
        // do pay logic

        // send status back to order service
        PaymentResponse response = new PaymentResponse(true, orderId, request.getTotalPrice(),
            PaymentStatus.PAID.toString(),"Paid Successfully~");
        producerService.notifyPaymentStatus(response);

        logger.info("kafka: finish do the payment and notify payment status to order service! ");
    }

    @KafkaListener(topics = "refund-request-topic", groupId = "orderRefund")
    public void reversePayment(String message) {
        logger.info("kafka: received message refund request is: ");
        // 将 JSON 字符串反序列化为 PaymentRequest 对象
        RefundRequest request = null;
        try {
            request = objectMapper.readValue(message, RefundRequest.class);
            logger.info(request.toString());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        logger.info("kafka: start refunding the order...");
        UUID orderId = request.getOrderId();
        // do pay logic

        // send status back to order service
        RefundResponse response = new RefundResponse(true, orderId, request.getTotalPrice(),
            PaymentStatus.REFUNDED.toString(),  "Refunded Successfully~");
        producerService.notifyFundStatus(response);

        logger.info("kafka: do the payment and notify payment status to order service! ");
    }
}
