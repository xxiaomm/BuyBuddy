package com.example.paymentservice.service;

import com.example.orderservice.controller.OrderController;
import com.example.orderservice.dto.PaymentResponse;
import com.example.orderservice.dto.RefundResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */


@Service
public class ProducerService {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);


    public void notifyPaymentStatus(PaymentResponse response) {
        try {
            String message = objectMapper.writeValueAsString(response);

            kafkaTemplate.send("payment-response-topic", message);
            logger.info("kafka: send payment response successfully~");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }

    public void notifyFundStatus(RefundResponse response) {
        try {
            String message = objectMapper.writeValueAsString(response);

            kafkaTemplate.send("refund-response-topic", message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
