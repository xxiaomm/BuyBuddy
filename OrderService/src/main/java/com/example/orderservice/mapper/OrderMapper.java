package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.enums.PaymentMethod;
import com.example.orderservice.service.Impl.OrderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Component
public class OrderMapper {

    @Autowired
    OrderItemMapper orderItemMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderMapper.class);


    // convert Order to OrderDto
    public OrderDto toOrderDto(Order order, List<OrderItem> orderItemList) {
        if (order == null) return null;
        logger.info("Converting Order to OrderDto: " + order);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getId().toString());
        orderDto.setUserId(order.getUserId().toString());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderStatus(order.getOrderStatus().toString());
        orderDto.setPaymentMethod(order.getPaymentMethod().toString());
        orderDto.setBillingAddress(order.getBillingAddress());
        orderDto.setShippingAddress(order.getShippingAddress());

//        if (order.getOrderItems().size() > 0) {
//            orderDto.setOrderItems(orderItemMapper.toOrderItemDtoList(order.getOrderItems()));
//        }
        orderDto.setOrderItems(orderItemMapper.toOrderItemDtoList(orderItemList));


        return orderDto;
    }

    public OrderDto toOrderDto(Order order) {
        if (order == null) return null;
        logger.info("Converting Order to OrderDto: " + order);
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getId().toString());
        orderDto.setUserId(order.getUserId().toString());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setOrderStatus(order.getOrderStatus().toString());
        orderDto.setPaymentMethod(order.getPaymentMethod().toString());
        orderDto.setBillingAddress(order.getBillingAddress());
        orderDto.setShippingAddress(order.getShippingAddress());

        if (order.getOrderItems().size() > 0) {
            orderDto.setOrderItems(orderItemMapper.toOrderItemDtoList(order.getOrderItems()));
        }

        return orderDto;
    }

    // convert OrderDto to Order
    public Order toOrder(OrderDto orderDto, List<OrderItemDto> orderItemDtoList) {
        if (orderDto == null) return null;
        logger.info("Converting OrderDto to Order: " + orderDto);
        Order order = new Order();
        order.setUserId(UUID.fromString(orderDto.getUserId()));
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderStatus(OrderStatus.valueOf(
            Optional.ofNullable(orderDto.getOrderStatus())
                .map(String::toUpperCase)
                .orElse("UNKNOWN")));
        order.setPaymentMethod(PaymentMethod.valueOf(Optional.ofNullable(orderDto.getPaymentMethod())
            .map(String::toUpperCase)
            .orElse("UNKNOWN")));
        order.setBillingAddress(orderDto.getBillingAddress());
        order.setShippingAddress(orderDto.getShippingAddress());

        order.setOrderItems(orderItemMapper.toOrderItemList(orderItemDtoList));

        return order;
    }

    public Order toOrder(Order order, OrderDto orderDto) {
        if (orderDto == null) return null;
        logger.info("Converting OrderDto to Order: " + orderDto);
        order.setUserId(UUID.fromString(orderDto.getUserId()));
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setOrderStatus(OrderStatus.valueOf(
            Optional.ofNullable(orderDto.getOrderStatus())
            .map(String::toUpperCase)
            .orElse("UNKNOWN")));
        order.setPaymentMethod(PaymentMethod.valueOf(Optional.ofNullable(orderDto.getPaymentMethod())
                .map(String::toUpperCase)
                .orElse("UNKNOWN")));
        order.setBillingAddress(orderDto.getBillingAddress());
        order.setShippingAddress(orderDto.getShippingAddress());

        if (orderDto.getOrderItems().size() > 0) {
            order.setOrderItems(orderItemMapper.toOrderItemList(orderDto.getOrderItems()));
        }

        return order;
    }

}
