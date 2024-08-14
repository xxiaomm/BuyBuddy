package com.example.orderservice.service;

import com.example.orderservice.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@Service
public interface OrderService {
    Order createOrder(Order order);
    Optional<Order> updateOrder(String orderId, Order order);
    Optional<Order> cancelOrder(String orderId);
    Optional<Order> getOrderById(String orderId);
    List<Order> getAllOrders();

//    Optional<List<Order>> searchOrder(String query);


}
