package com.example.orderservice.repository;

import com.example.orderservice.entity.Order;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Repository
public interface OrderRepository extends CassandraRepository<Order, UUID> {

}
