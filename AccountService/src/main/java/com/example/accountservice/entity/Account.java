package com.example.accountservice.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Table
public class Account {
    @Id
    String id;
    String password;

}
