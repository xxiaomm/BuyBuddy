package com.example.orderservice.dto;

import com.example.itemservice.dto.ItemDto;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Order Item Dto")
public class OrderItemDto {
    private String itemId;
    private double price;  // Item.price may be changed
    private int quantity;
}
