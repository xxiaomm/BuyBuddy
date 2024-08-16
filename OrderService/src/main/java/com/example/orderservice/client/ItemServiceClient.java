//package com.example.orderservice.client;
//
//import com.example.itemservice.common.Response;
//import com.example.itemservice.dto.ItemDto;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//
//import java.util.Optional;
//
///**
// * @Create 08/2024
// * @Author xiao
// * @Description
// */
//
//@FeignClient(name = "ItemService") // url = "http://localhost:8761", path="/ItemService"
//public interface ItemServiceClient {
//
//    @GetMapping("/items/{id}")
//    ResponseEntity<Response<ItemDto>> getItemById(@PathVariable String id);
//}
