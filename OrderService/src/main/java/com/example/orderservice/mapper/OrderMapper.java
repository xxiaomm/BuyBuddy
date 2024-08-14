package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.entity.Order;
import org.springframework.stereotype.Component;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Component
public class OrderMapper {

    // convert Order to OrderDto
    public OrderDto toOrderDto(Order order) {
        if (order == null) return null;
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getUserId());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setBillingAddress(order.getBillingAddress());
        orderDto.setShippingAddress(order.getShippingAddress());
//        orderDto.setItems(order.getItems());

        return orderDto;
    }

    // convert OrderDto to Order
    public Order toOrder(OrderDto orderDto) {
        if (orderDto == null) return null;
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setBillingAddress(orderDto.getBillingAddress());
        order.setShippingAddress(orderDto.getShippingAddress());
//        order.setItems(orderDto.getItems());

        return order;
    }

}
