package com.example.itemservice.mapper;

import com.example.itemservice.dto.ItemDto;
import com.example.itemservice.entity.Item;
import org.springframework.stereotype.Component;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Component
public class ItemMapper {

    // convert Item to ItemDto
    public ItemDto toItemDto(Item item) {
        if (item == null) return null;
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setDescription(item.getDescription());
        itemDto.setPrice(item.getPrice());
        itemDto.setUpc(item.getUpc());
        itemDto.setInventory(item.getInventory());
        itemDto.setImageURL(item.getImageURL());

        return itemDto;
    }

    // convert ItemDto to Item
    public Item toItem(ItemDto itemDto) {
        if (itemDto == null) return null;
        Item item = new Item();

        item.setId(itemDto.getId());
        item.setName(itemDto.getName());
        item.setDescription(itemDto.getDescription());
        item.setPrice(itemDto.getPrice());
        item.setUpc(itemDto.getUpc());
        item.setInventory(itemDto.getInventory());
        item.setImageURL(item.getImageURL());

        return item;
    }
}
