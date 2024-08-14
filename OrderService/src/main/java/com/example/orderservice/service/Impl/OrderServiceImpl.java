package com.example.orderservice.service.Impl;

import com.example.orderservice.entity.Order;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

//    @Autowired
//    private KafkaTemplate<String, Order> kafkaTemplate;

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public Optional<Order> updateOrder(String orderId, Order order) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> cancelOrder(String orderId) {
        return Optional.empty();
    }

    @Override
    public Optional<Order> getOrderById(String orderId) {
        return Optional.empty();
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }
}
