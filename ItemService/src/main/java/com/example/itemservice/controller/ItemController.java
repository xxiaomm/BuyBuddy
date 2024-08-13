package com.example.itemservice.controller;

import com.example.itemservice.common.Response;
import com.example.itemservice.entity.Item;
import com.example.itemservice.service.ItemService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
//@Api(value = "Item Controller", description = "APIs related to Item Management")
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService itemService;


    @PostMapping
    public ResponseEntity<Response<Item>> createItem(@RequestBody Item itemDetails) {
        Item createdItem = itemService.createItem(itemDetails);
        Response<Item> response = new Response<>(true, createdItem, "Item successfully created");
        return ResponseEntity.ok(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Response<Item>> updateItem(@PathVariable String id, @RequestBody Item itemDetails) {
        Optional<Item> updatedItem = itemService.updateItem(id, itemDetails);
        if (updatedItem.isPresent()) {
            Response<Item> response = new Response<>(true, updatedItem.get(), "Item successfully updated");
            return ResponseEntity.ok(response);
        } else {
            Response<Item> response = new Response<>(false, null, "Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Item>> deleteItemById(@PathVariable String id) {
        Optional<Item> deletedItem = itemService.deleteItemById(id);
        if (deletedItem.isPresent()) {
            Response<Item> response = new Response<>(true, deletedItem.get(), "Item successfully deleted");
            return ResponseEntity.ok(response);
        } else {
            Response<Item> response = new Response<>(false, null, "Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

//    @GetMapping
//    public ResponseEntity<Response<Item>> getItemById(@RequestParam(name = "id") String id) {
////        ObjectId objectId = new ObjectId(id);
//        Optional<Item> item = itemService.getItemById(id);
//        if (item.isPresent()) {
//            Response<Item> response = new Response<>(true, item.get(), "Item found");
//            return ResponseEntity.ok(response);
//        } else {
//            Response<Item> response = new Response<>(false, null, "Item not found");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }

    @GetMapping("/{id}") // , produces = MediaType.APPLICATION_JSON_VALUE
    public ResponseEntity<Response<Item>> getItemById(@PathVariable String id) {
//        ObjectId objectId = new ObjectId(id);
        Optional<Item> item = itemService.getItemById(id);
        if (item.isPresent()) {
            Response<Item> response = new Response<>(true, item.get(), "Item found");
            return ResponseEntity.ok(response);
        } else {
            Response<Item> response = new Response<>(false, null, "Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @GetMapping
    public ResponseEntity<Response<List<Item>>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        Response<List<Item>> response = new Response<>(true, items, "Items retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/inventory")
    public ResponseEntity<Response<Integer>> getInventory(@PathVariable String id) {
        int inventory = itemService.getInventory(id);
        Response<Integer> response = new Response<>(true, inventory, "Inventory retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/inventory")
    public ResponseEntity<Response<Integer>> updateInventory(@PathVariable String id, @RequestParam int soldAmount) {
        int updatedInventory = itemService.updateInventory(id, soldAmount);
        Response<Integer> response = new Response<>(true, updatedInventory, "Inventory updated successfully");
        return ResponseEntity.ok(response);
    }
}
