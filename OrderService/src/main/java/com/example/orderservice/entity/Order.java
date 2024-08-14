package com.example.orderservice.entity;

import com.example.itemservice.entity.Item;
import com.example.orderservice.enums.OrderStatus;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.List;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
// https://juejin.cn/post/7132091848159395848

@Table("orders")
public class Order {
    @PrimaryKey
    private UUID orderId;

    private String userId;
    private double totalPrice;
    private OrderStatus orderStatus;
    @CassandraType(type = CassandraType.Name.LIST)
    private List<Item> items;
    private String Address;
    private String paymentMethod;

    private String createdAt;
    private String updatedAt;



}
