package com.example.itemservice.service;

import com.example.itemservice.dto.ItemDto;
import com.example.itemservice.entity.Item;
import com.example.itemservice.enums.InventoryOperation;
import org.springframework.stereotype.Component;
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

    Optional<ItemDto> getItemById(String id);

    ItemDto createItem(ItemDto itemDto);

    Optional<ItemDto> updateItem(String id, ItemDto itemDetails);

    Optional<ItemDto> deleteItemById(String id);

    List<ItemDto> getAllItems();


    int getInventory(String id);
    int updateInventory(String id, int amount, InventoryOperation operation);


}
