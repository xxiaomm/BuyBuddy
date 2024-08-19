package com.example.orderservice.dto;

import com.example.itemservice.entity.Item;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.enums.PaymentMethod;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "OrderDto")
public class OrderDto {

    private String orderId;
    private String userId;
    private String orderStatus;
    private String paymentMethod;
    private double totalPrice;
    private String shippingAddress;
    private String billingAddress;
    private List<OrderItemDto> orderItems;

}
