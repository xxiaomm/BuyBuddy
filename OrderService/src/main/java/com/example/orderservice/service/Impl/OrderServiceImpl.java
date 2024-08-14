package com.example.orderservice.service.Impl;

import com.example.itemservice.dto.ItemDto;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.itemservice.service.ItemService;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@ComponentScan(basePackages = "com.example.itemservice")
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private KafkaTemplate<String, Order> kafkaTemplate;

    @Autowired
    private ItemService itemService; // Inject ItemService


    /**
     * TODO: check inventory from item-service
     * 可以先提交订单，但是之后再check？
     */
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = orderMapper.toOrder(orderDto);
        order.setId(UUID.randomUUID());
        order.setCreateTime(Instant.now());
        order.setUpdateTime(Instant.now());
        order.setOrderStatus(OrderStatus.CREATE);

        double totalPrice = calculateTotalPrice(orderDto.getItems());
        order.setTotalPrice(totalPrice);

        Order savedOrder = orderRepository.save(order);

        // Publish order creation event to Kafka
//        kafkaTemplate.send("order-topic", savedOrder);


        return orderMapper.toOrderDto(savedOrder);
    }

    @Override
    public Optional<OrderDto> updateOrderStatus(UUID orderId, OrderStatus orderStatus) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setUpdateTime(Instant.now());
            order.setOrderStatus(orderStatus);

            Order updatedOrder = orderRepository.save(order);

            return Optional.of(orderMapper.toOrderDto(updatedOrder));
        } else {
            // Throw custom exception if the order is not found
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    @Override
    public Optional<OrderDto> updateShippingAddress(UUID orderId, String shippingAddress) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setUpdateTime(Instant.now());
            order.setShippingAddress(shippingAddress);

            Order updatedOrder = orderRepository.save(order);

            return Optional.of(orderMapper.toOrderDto(updatedOrder));
        } else {
            // Throw custom exception if the order is not found
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    @Override
    public Optional<OrderDto> updateBillingAddress(UUID orderId, String billingAddress) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setUpdateTime(Instant.now());
            order.setBillingAddress(billingAddress);

            Order updatedOrder = orderRepository.save(order);

            return Optional.of(orderMapper.toOrderDto(updatedOrder));
        } else {
            // Throw custom exception if the order is not found
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

//    @Override
//    public Optional<OrderDto> updateOrder(UUID orderId, OrderDto orderDto) {
//        Optional<Order> orderOptional = orderRepository.findById(orderId);
//        if (orderOptional.isPresent()) {
//            Order order = orderOptional.get();
//            order.setUpdateTime(Instant.now());
//            order.setOrderStatus(orderDto.getOrderStatus());
//            order.setPaymentMethod(orderDto.getPaymentMethod());
//            order.setShippingAddress(orderDto.getShippingAddress());
//            order.setBillingAddress(orderDto.getBillingAddress());
//
//            double totalPrice = calculateTotalPrice(orderDto.getItems());
//            order.setTotalPrice(totalPrice);
//
//            Order updatedOrder = orderRepository.save(order);
//
//            // Publish order update event to Kafka
////            kafkaTemplate.send("order-update-topic", updatedOrder);
//
//            return Optional.of(orderMapper.toOrderDto(updatedOrder));
//        }
//        return Optional.empty();
//    }

    @Override
    public Optional<OrderDto> cancelOrder(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setUpdateTime(Instant.now());
            order.setOrderStatus(OrderStatus.CANCELLED);

            Order canceledOrder = orderRepository.save(order);

            // Publish order cancellation event to Kafka
//            kafkaTemplate.send("order-cancel-topic", canceledOrder);

            return Optional.of(orderMapper.toOrderDto(canceledOrder));
        } else {
            // Throw custom exception if the order is not found
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    @Override
    public Optional<OrderDto> getOrderById(UUID orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if (orderOptional.isPresent()) {
            return orderOptional.map(orderMapper::toOrderDto);
        } else {
            // Throw custom exception if the order is not found
            throw new OrderNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toOrderDto).collect(Collectors.toList());

    }


    private double calculateTotalPrice(List<OrderItemDto> orderItems) {
        return orderItems.stream()
            .mapToDouble(orderItemDto -> {
                // Fetch item details from ItemService
                Optional<ItemDto> itemDtoOptional = itemService.getItemById(orderItemDto.getItemId());
                if (itemDtoOptional.isPresent()) {
                    ItemDto itemDto = itemDtoOptional.get();
                    return itemDto.getPrice() * orderItemDto.getQuantity();
                }
                return 0.0; // Default to 0 if item is not found
            })
            .sum();
    }
}

