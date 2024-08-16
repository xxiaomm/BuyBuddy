package com.example.itemservice.controller;

import com.example.itemservice.common.Response;
import com.example.itemservice.dto.ItemDto;
import com.example.itemservice.entity.Item;
import com.example.itemservice.enums.InventoryOperation;
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
    public ResponseEntity<Response<ItemDto>> createItem(@RequestBody ItemDto itemDetails) {
        ItemDto createdItem = itemService.createItem(itemDetails);
        Response<ItemDto> response = new Response<>(true, createdItem, "Item successfully created");
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "Update Item REST API")
    @PutMapping("/{id}")
    public ResponseEntity<Response<ItemDto>> updateItem(@PathVariable String id, @RequestBody ItemDto itemDetails) {
        Optional<ItemDto> updatedItem = itemService.updateItem(id, itemDetails);
        if (updatedItem.isPresent()) {
            Response<ItemDto> response = new Response<>(true, updatedItem.get(), "Item successfully updated");
            return ResponseEntity.ok(response);
        } else {
            Response<ItemDto> response = new Response<>(false, null, "Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @ApiOperation(value = "Delete Item with Id REST API")
    @DeleteMapping("/{id}")
    public ResponseEntity<Response<ItemDto>> deleteItemById(@PathVariable String id) {
        Optional<ItemDto> deletedItem = itemService.deleteItemById(id);
        if (deletedItem.isPresent()) {
            Response<ItemDto> response = new Response<>(true, deletedItem.get(), "Item successfully deleted");
            return ResponseEntity.ok(response);
        } else {
            Response<ItemDto> response = new Response<>(false, null, "Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

//    @GetMapping
//    public ResponseEntity<Response<ItemDto>> getItemById(@RequestParam(name = "id") String id) {
////        ObjectId objectId = new ObjectId(id);
//        Optional<ItemDto> item = itemService.getItemById(id);
//        if (item.isPresent()) {
//            Response<ItemDto> response = new Response<>(true, item.get(), "Item found");
//            return ResponseEntity.ok(response);
//        } else {
//            Response<ItemDto> response = new Response<>(false, null, "Item not found");
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
//        }
//    }

    @ApiOperation(value = "Get Item with Id REST API")
    @GetMapping("/{id}") // , produces = MediaType.APPLICATION_JSON_VALUE
    public ResponseEntity<Response<ItemDto>> getItemById(@PathVariable String id) {
//        ObjectId objectId = new ObjectId(id);
        Optional<ItemDto> item = itemService.getItemById(id);
        if (item.isPresent()) {
            Response<ItemDto> response = new Response<>(true, item.get(), "Item found");
            return ResponseEntity.ok(response);
        } else {
            Response<ItemDto> response = new Response<>(false, null, "Item not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @ApiOperation(value = "Get All Items REST API")
    @GetMapping
    public ResponseEntity<Response<List<ItemDto>>> getAllItems() {
        List<ItemDto> items = itemService.getAllItems();
        Response<List<ItemDto>> response = new Response<>(true, items, "Items retrieved successfully");
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
    public ResponseEntity<?> updateInventory(@PathVariable String id,
                                                             @RequestParam("amount") int amount,
                                                             @RequestParam("operation") String operation) {
        InventoryOperation op;
        try {
            op = InventoryOperation.valueOf(operation.toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid operation type");
        }

        int updatedInventory = itemService.updateInventory(id, amount, op);
        Response<Integer> response = new Response<>(true, updatedInventory, "Inventory updated successfully");
        return ResponseEntity.ok(response);
    }
}
