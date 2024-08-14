package com.example.orderservice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Api(value = "order-controller")
@RestController
@RequestMapping("/orders")
public class OrderController {



//    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Order REST API")
    @PostMapping
    public void createOrder() {

    }

    @ApiOperation(value = "Update Order REST API")
    @PutMapping
    public void updateOrder() {

    }

    @ApiOperation(value = "Cancel Order REST API")
    @DeleteMapping
    public void cancelOrder() {

    }

    @ApiOperation(value = "Search Oder REST API")
    @GetMapping
    public void searchOrder() {

    }

    @ApiOperation(value = "Get All Orders REST API")
    @GetMapping
    public void getAllOrders() {

    }
}
