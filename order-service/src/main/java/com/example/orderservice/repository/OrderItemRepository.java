package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.entity.OrderItemKey;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Repository
public interface OrderItemRepository extends CassandraRepository<OrderItem, OrderItemKey> {


    @Query("SELECT * FROM order_items WHERE order_id = ?0")
    List<OrderItem> findByIdOrderId(UUID orderId);

    @Query("DELETE FROM order_items WHERE order_id = ?0")
    List<OrderItem> deleteByIdOrderId(UUID orderId);

}
