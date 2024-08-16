package com.example.accountservice.entity;

import com.example.accountservice.constant.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @Column(name= "username", unique = true, nullable = false)
    private String username;

    @Column(name= "email", unique = true, nullable = false)
    private String email;

    @Column(name= "password", nullable = false)
    private String password;
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name= "shipping_address")
    private String shippingAddress;

    @Column(name= "billing_address")
    private String billingAddress;
    @Column(name= "payment_method")
    private PaymentMethod paymentMethod;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles;


}
