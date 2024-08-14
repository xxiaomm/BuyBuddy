package com.example.itemservice.controller;

import com.example.itemservice.common.Response;
import com.example.itemservice.entity.Item;
import com.example.itemservice.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Api(value = "item-controller") // description = "", tag = {"",""}
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService itemService;


    @ApiOperation(value = "Create Item REST API")
    @PostMapping
    public ResponseEntity<Response<Item>> createItem(@RequestBody Item itemDetails) {
        Item createdItem = itemService.createItem(itemDetails);
        Response<Item> response = new Response<>(true, createdItem, "Item successfully created");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Update Item REST API")
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

    @ApiOperation(value = "Delete Item with Id REST API")
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

    @ApiOperation(value = "Get Item with Id REST API")
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

    @ApiOperation(value = "Get All Items REST API")
    @GetMapping
    public ResponseEntity<Response<List<Item>>> getAllItems() {
        List<Item> items = itemService.getAllItems();
        Response<List<Item>> response = new Response<>(true, items, "Items retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Get Inventory of an Item REST API")
    @GetMapping("/{id}/inventory")
    public ResponseEntity<Response<Integer>> getInventory(@PathVariable String id) {
        int inventory = itemService.getInventory(id);
        Response<Integer> response = new Response<>(true, inventory, "Inventory retrieved successfully");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Update Inventory of an Item REST API")
    @PutMapping("/{id}/inventory")
    public ResponseEntity<Response<Integer>> updateInventory(@PathVariable String id, @RequestParam int soldAmount) {
        int updatedInventory = itemService.updateInventory(id, soldAmount);
        Response<Integer> response = new Response<>(true, updatedInventory, "Inventory updated successfully");
        return ResponseEntity.ok(response);
    }
}
