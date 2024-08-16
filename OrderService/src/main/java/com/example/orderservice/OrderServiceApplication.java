package com.example.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;


//@EnableConfigurationProperties(CassandraProperties.class)
//@EnableEurekaServer
//@EnableDiscoveryClient
//@EnableFeignClients // 启用 Feign 客户端
@EnableCassandraRepositories(basePackages = "com.example.orderservice.repository")
@SpringBootApplication
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
