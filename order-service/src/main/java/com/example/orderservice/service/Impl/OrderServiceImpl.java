package com.example.orderservice.service.Impl;

//import com.example.orderservice.client.itemserviceceClient;
import com.example.orderservice.dto.PaymentRequest;
import com.example.orderservice.dto.RefundRequest;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.enums.PaymentMethod;
import com.example.orderservice.exception.OrderNotFoundException;
import com.example.itemservice.service.ItemService;
import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.mapper.OrderItemMapper;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.repository.OrderItemRepository;
import com.example.orderservice.repository.OrderRepository;
import com.example.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
    import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
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
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

//    @Autowired
//    private ItemServiceClient itemServiceClient;

    @Autowired
    private ItemService itemService; // Inject ItemService

    private final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    /**
     * TODO: check inventory from item-service
     * 可以先提交订单，但是之后再check？
     */
    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        orderMapper.toOrder(order, orderDto);
        Instant now = Instant.now();
        order.setCreateTime(now);
        order.setUpdateTime(now);

        order.setOrderStatus(OrderStatus.CREATE);

        List<OrderItem> orderItems = new LinkedList<>();
        List<OrderItemDto> orderItemDtos = orderDto.getOrderItems();
        logger.info("before calculate price order: "+ order.toString());

        double totalPrice = 0;

        for (OrderItemDto itemDto : orderItemDtos) {
            OrderItem orderItem = new OrderItem(order.getId(),
                UUID.fromString(itemDto.getItemId()), itemDto.getPrice(), itemDto.getQuantity());

            // 计算总价
            totalPrice += itemDto.getPrice() * itemDto.getQuantity();

            // 保存订单项
            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        order.setTotalPrice(totalPrice);
        Order savedOrder = orderRepository.save(order);

        // Publish order creation event to Kafka
        logger.info("order service send created to Payment service to pay!");

        // send create order finish to payment service to make a payment
        PaymentRequest request = new PaymentRequest(savedOrder.getId(), savedOrder.getTotalPrice(),
            savedOrder.getPaymentMethod(), "Please do payment for the order~");
        try {

            String orderJson = objectMapper.writeValueAsString(request);
            kafkaTemplate.send("payment-request-topic", orderJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            // Handle exception
        }
        logger.info("payment request is: " + request.toString());

        return orderMapper.toOrderDto(savedOrder, orderItems);
    }

    @Override
    public Optional<OrderDto> refundOrder(UUID orderId) {
        try {
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setOrderItems(orderItemRepository.findByIdOrderId(orderId));
                RefundRequest request = new RefundRequest(orderId, order.getTotalPrice(),
                    order.getPaymentMethod(),  "Please make a refund of the order! ");

                String orderJson = objectMapper.writeValueAsString(request);
                kafkaTemplate.send("refund-request-topic", orderJson);

                return Optional.of(orderMapper.toOrderDto(order));
            } else {
                // Throw custom exception if the order is not found
                throw new OrderNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();

    }


    @Override
    public Optional<OrderDto> getOrderById(UUID orderId) {
        try {
            Optional<Order> order = orderRepository.findById(orderId);

            if (order.isPresent()) {
                List<OrderItem> orderItemList = orderItemRepository.findByIdOrderId(order.get().getId());
                order.get().setOrderItems(orderItemList);
                Optional<OrderDto> orderDto = Optional.of(orderMapper.toOrderDto(order.get()));
                return orderDto;
            } else {
                // Throw custom exception if the order is not found
                throw new OrderNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public Optional<OrderDto> updateOrderStatus(UUID orderId, OrderStatus orderStatus) {
        try {
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setUpdateTime(Instant.now());
                order.setOrderStatus(orderStatus);

                List<OrderItem> orderItemList = orderItemRepository.findByIdOrderId(orderId);
                order.setOrderItems(orderItemList);

                Order updatedOrder = orderRepository.save(order);

                return Optional.of(orderMapper.toOrderDto(updatedOrder));
            } else {
                // Throw custom exception if the order is not found
                throw new OrderNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public Optional<OrderDto> updatePaymentMethod(UUID orderId, PaymentMethod paymentMethod) {
        try {
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setUpdateTime(Instant.now());
                order.setPaymentMethod(paymentMethod);

                List<OrderItem> orderItemList = orderItemRepository.findByIdOrderId(orderId);
                order.setOrderItems(orderItemList);

                Order updatedOrder = orderRepository.save(order);

                return Optional.of(orderMapper.toOrderDto(updatedOrder));
            } else {
                // Throw custom exception if the order is not found
                throw new OrderNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<OrderDto> updateShippingAddress(UUID orderId, String shippingAddress) {
        try{
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setUpdateTime(Instant.now());
                order.setShippingAddress(shippingAddress);

                List<OrderItem> orderItemList = orderItemRepository.findByIdOrderId(orderId);
                order.setOrderItems(orderItemList);

                Order updatedOrder = orderRepository.save(order);

                return Optional.of(orderMapper.toOrderDto(updatedOrder));
            } else {
                // Throw custom exception if the order is not found
                throw new OrderNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<OrderDto> updateBillingAddress(UUID orderId, String billingAddress) {
        try{
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setUpdateTime(Instant.now());
                order.setBillingAddress(billingAddress);

                List<OrderItem> orderItemList = orderItemRepository.findByIdOrderId(orderId);
                order.setOrderItems(orderItemList);

                Order updatedOrder = orderRepository.save(order);

                return Optional.of(orderMapper.toOrderDto(updatedOrder));
            } else {
                // Throw custom exception if the order is not found
                throw new OrderNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public Optional<OrderDto> cancelOrder(UUID orderId) {
        try {
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                Order order = orderOptional.get();
                order.setUpdateTime(Instant.now());
                order.setOrderStatus(OrderStatus.CANCELLED);
                order.setOrderItems(orderItemRepository.findByIdOrderId(orderId));
//                orderRepository.delete(order);
//
//                List<OrderItem> orderItemList = orderItemRepository.findByIdOrderId(orderId);
//                order.setOrderItems(orderItemList);
//
//                orderItemRepository.deleteByIdOrderId(orderId);

                // Publish order cancellation event to Kafka
//            kafkaTemplate.send("order-cancel-topic", canceledOrder);

                return Optional.of(orderMapper.toOrderDto(order));
            } else {
                // Throw custom exception if the order is not found
                throw new OrderNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }



    @Override
    public Optional<List<OrderItemDto>> getAllItemsByOrderId(UUID orderId) {
        try {
            Optional<Order> order = orderRepository.findById(orderId);
            logger.info("Find order by id successfully!" );
            if (order.isPresent()) {
                List<OrderItem> orderItemList = orderItemRepository.findByIdOrderId(order.get().getId());
                logger.info("Find order by id successfully! + "+orderItemList.size() +"\n" + orderItemList.toString()
                +"\n"+orderItemRepository.findByIdOrderId(orderId));

                List<OrderItemDto> orderItemDtos = orderItemMapper.toOrderItemDtoList(orderItemList);

                logger.info("Find order by id successfully! +size: "+ orderItemDtos.size() +"items: "+orderItemDtos.toString());

                return Optional.of(orderItemDtos);
            } else {
                // Throw custom exception if the order is not found
                throw new OrderNotFoundException("Order with ID " + orderId + " not found");
            }
        } catch (OrderNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }


    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtos = new ArrayList<>();

//        for (Order order : orders) {
//            List<OrderItem> orderItems = orderItemRepository.findByIdOrderId(order.getId());
//            order.setOrderItems(orderItems);
//            OrderDto orderDto = orderMapper.toOrderDto(order);
//            orderDtos.add(orderDto);
//        }
        // 使用 Stream API 遍历订单列表
        return orders.stream().map(order -> {
            // 查找与每个订单相关的订单项
            List<OrderItem> orderItems = orderItemRepository.findByIdOrderId(order.getId());

            // 将订单项设置到订单中
            order.setOrderItems(orderItems);

            // 将订单转换为 DTO
            return orderMapper.toOrderDto(order);
        }).collect(Collectors.toList());
    }

}

