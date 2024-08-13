package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import org.springframework.stereotype.Service;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@Service
public interface OrderService {
    Order createOrder();
}
