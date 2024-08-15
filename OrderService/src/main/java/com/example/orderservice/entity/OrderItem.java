package com.example.orderservice.entity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Order Item Entity")
@Table("OrderItem")
public class OrderItem {
    @PrimaryKey
    private String itemId;

    @Column("price")
    private int price;  // Item.price may be changed

    @Column("quantity")
    private int quantity;
}
