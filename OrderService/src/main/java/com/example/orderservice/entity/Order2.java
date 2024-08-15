//package com.example.orderservice.entity;
//
//import com.example.orderservice.enums.OrderStatus;
//import io.swagger.annotations.ApiModel;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//
//import java.time.Instant;
//import java.util.List;
//import java.util.UUID;
//
///**
// * @Create 08/2024
// * @Author xiao
// * @Description
// */
//// https://juejin.cn/post/7132091848159395848
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@ApiModel(value = "Order Entity")
//@Entity
//@Table(name = "orders")
//public class Order {
//    @Id
////    @PrimaryKey
//    private UUID id;
//
//    private String userId;
//
////    @Column("total_price")
//    private double totalPrice;
//
////    @Column("order_status")
//    private OrderStatus orderStatus;
//
////    @Column("items")
////    @CassandraType(type = CassandraType.Name.LIST, typeArguments = CassandraType.Name.UDT)// UserDefineType
//
//    @OneToMany
//    private List<OrderItem> items;
//
////    @Column("shipping_address")
//    private String shippingAddress;
//
////    @Column("billing_address")
//    private String billingAddress;
//
////    @Column("payment_method")
//    private String paymentMethod;
//
//    @CreatedDate
////    @Column("create_time")
////    @CassandraType(type = CassandraType.Name.TIMESTAMP)
//    private Instant createTime;
//
//    @LastModifiedDate
////    @Column("update_time")
////    @CassandraType(type = CassandraType.Name.TIMESTAMP)
//    private Instant updateTime;
//
////    public Order() {
////        this.id = Uuids.timeBased(); // 使用时间戳生成 UUID
////    }
//
//
//}
