package com.example.itemservice.service;

import com.example.itemservice.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Service
public interface ItemService {

    Optional<Item> getItemById(String id);

    Item createItem(Item item);

    Optional<Item> updateItem(String id, Item itemDetails);

    Optional<Item> deleteItemById(String id);

    List<Item> getAllItems();


    int getInventory(String id);
    int updateInventory(String id, int soldAmount);


}
