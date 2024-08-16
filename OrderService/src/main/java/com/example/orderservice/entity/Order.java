package com.example.orderservice.entity;

import com.datastax.oss.driver.api.core.uuid.Uuids;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.enums.PaymentMethod;
import io.swagger.annotations.ApiModel;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
// https://juejin.cn/post/7132091848159395848

@Data
@AllArgsConstructor
@ApiModel(value = "Order Entity")
@Table("orders")
public class Order {
    @PrimaryKey
    private UUID id;

    @Column("user_id")
    private UUID userId;

    @Column("total_price")
    private double totalPrice;

    @Column("order_status")
    private OrderStatus orderStatus;

//    @ElementCollection
//    @Column("items")
//    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.UDT)// UserDefineType
//    private List<OrderItem> items;

    @Transient
    private List<OrderItem> orderItems; // 暂存订单项


    @Column("shipping_address")
    private String shippingAddress;

    @Column("billing_address")
    private String billingAddress;

    @Column("payment_method")
    private PaymentMethod paymentMethod;

    @CreatedDate
    @Column("create_time")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Instant createTime;

    @LastModifiedDate
    @Column("update_time")
    @CassandraType(type = CassandraType.Name.TIMESTAMP)
    private Instant updateTime;

    public Order() {
        this.id = Uuids.timeBased(); // 使用时间戳生成 UUID
    }


}
