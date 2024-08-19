package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
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
    private KafkaTemplate<String, OrderDto> kafkaTemplate;


    // should have a shipping service
    public void notifyPaidStatus(OrderDto orderDto) {
        kafkaTemplate.send("shipping-topic", orderDto);
    }
}
