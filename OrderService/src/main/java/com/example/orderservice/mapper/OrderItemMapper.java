package com.example.orderservice.mapper;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Component
public class OrderItemMapper {

    // convert OrderItem to OrderItemDto
    public OrderItemDto toOrderItemDto(OrderItem orderItem) {
        if (orderItem == null) return null;
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setItemId(orderItem.getItemId().toString());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setQuantity(orderItem.getQuantity());

        return orderItemDto;
    }

    // convert OrderItemDto to OrderItem
    public OrderItem toOrderItem(OrderItemDto orderItemDto) {
        if (orderItemDto == null) return null;
        OrderItem orderItem = new OrderItem();
//        OrderItemKey key = new OrderItemKey(orderId, UUID.fromString(itemDto.getItemId()));
        orderItem.setPrice(orderItemDto.getPrice());
        orderItem.setQuantity(orderItemDto.getQuantity());

        return orderItem;
    }

    // Convert List<OrderItem> to List<OrderItemDto>
    public List<OrderItemDto> toOrderItemDtoList(List<OrderItem> orderItemList) {
        if (orderItemList.size() == 0) return null;
        return orderItemList.stream()
            .map(this::toOrderItemDto)
            .collect(Collectors.toList());
    }

    // Convert List<OrderItemDto> to List<OrderItem>
    public List<OrderItem> toOrderItemList(List<OrderItemDto> orderItemDtoList) {
        if (orderItemDtoList == null) return null;
        return orderItemDtoList.stream()
            .map(this::toOrderItem)
            .collect(Collectors.toList());
    }

}
