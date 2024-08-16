package com.example.orderservice.service;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.enums.PaymentMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@Service
public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);
//    Optional<OrderDto> updateOrder(UUID orderId, OrderDto orderDto);

    Optional<OrderDto> updateOrderStatus(UUID orderId, OrderStatus orderStatus);
    Optional<OrderDto> updatePaymentMethod(UUID orderId, PaymentMethod paymentMethod);
    Optional<OrderDto> updateShippingAddress(UUID orderId, String shippingAddress);
    Optional<OrderDto> updateBillingAddress(UUID orderId, String billingAddress);


    Optional<OrderDto> cancelOrder(UUID orderId);
    Optional<OrderDto> getOrderById(UUID orderId);

    public Optional<List<OrderItemDto>> getAllItemsByOrderId(UUID orderId);

    List<OrderDto> getAllOrders();

//    Optional<List<Order>> searchOrder(String query);


}
