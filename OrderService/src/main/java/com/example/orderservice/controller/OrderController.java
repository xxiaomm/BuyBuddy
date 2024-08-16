package com.example.orderservice.controller;

import com.example.orderservice.dto.OrderDto;
import com.example.orderservice.dto.OrderItemDto;
import com.example.orderservice.entity.OrderItem;
import com.example.orderservice.enums.OrderStatus;
import com.example.orderservice.enums.PaymentMethod;
import com.example.orderservice.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Api(value = "order-controller")
@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    //    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create Order REST API")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDetails) {
        OrderDto orderDto = orderService.createOrder(orderDetails);
        return ResponseEntity.ok(orderDto);
    }

    @ApiOperation(value = "Get Order By Id REST API")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id){
        UUID uuid = UUID.fromString(id);
        Optional<OrderDto> orderDto = orderService.getOrderById(uuid);
        return orderDto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Get All Items by Order Id REST API")
    @GetMapping("/{id}/items")
    public ResponseEntity<List<OrderItemDto>> getAllItemsByOrderId(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        Optional<List<OrderItemDto>> orderDtos = orderService.getAllItemsByOrderId(uuid);
        return ResponseEntity.ok(orderDtos.get());
    }


//    @ApiOperation(value = "Update Order REST API")
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateOrder(@PathVariable String id, @RequestBody OrderDto orderDetails) {
//        UUID uuid = UUID.fromString(id);
//        Optional<OrderDto> orderDto = orderService.getOrderById(uuid);
//        return ResponseEntity.ok(orderDto);
//    }

    @ApiOperation(value = "Cancel Order REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancelOrder(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        Optional<OrderDto> orderDto = orderService.cancelOrder(uuid);
        return ResponseEntity.ok(orderDto);
    }

    @ApiOperation(value = "Update Order Status REST API")
    @PutMapping("/{id}/status/{order_status}")
    public ResponseEntity<?> updateOrderStatus(@PathVariable String id, @PathVariable("order_status") String orderStatus) {
        UUID uuid = UUID.fromString(id);
        OrderStatus status = OrderStatus.valueOf(orderStatus.toUpperCase());
        Optional<OrderDto> orderDto = orderService.updateOrderStatus(uuid, status);
        return ResponseEntity.ok(orderDto);
    }

    @ApiOperation(value = "Update Payment Method REST API")
    @PutMapping("/{id}/payment/{payment_method}")
    public ResponseEntity<?> updatePaymentMethod(@PathVariable String id, @PathVariable("payment_method") String paymentMethod) {
        UUID uuid = UUID.fromString(id);
        PaymentMethod method = PaymentMethod.valueOf(paymentMethod.toUpperCase());
        Optional<OrderDto> orderDto = orderService.updatePaymentMethod(uuid, method);
        return ResponseEntity.ok(orderDto);
    }

    @ApiOperation(value = "Update Order shipping address REST API")
    @PutMapping("/{id}/shipping/{shipping_address}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable String id, @PathVariable("shipping_address") String shippingAddress) {
        UUID uuid = UUID.fromString(id);
        Optional<OrderDto> orderDto = orderService.updateShippingAddress(uuid, shippingAddress);
        return ResponseEntity.ok(orderDto);
    }

    @ApiOperation(value = "Update Order billing address REST API")
    @PutMapping("/{id}/billing/{billing_address}")
    public ResponseEntity<?> updateBillingAddress(@PathVariable String id, @PathVariable("billing_address") String billingAddress) {
        UUID uuid = UUID.fromString(id);
        Optional<OrderDto> orderDto = orderService.updateBillingAddress(uuid, billingAddress);
        return ResponseEntity.ok(orderDto);
    }


    @ApiOperation(value = "Get All Orders REST API")
    @GetMapping
    public ResponseEntity<?> getAllOrders() {
        List<OrderDto> orderDtos = orderService.getAllOrders();
        return ResponseEntity.ok(orderDtos);
    }

//    @ApiOperation(value = "Search Oder REST API")
//    @GetMapping
//    public ResponseEntity<?> searchOrder() {
//
//    }
}
