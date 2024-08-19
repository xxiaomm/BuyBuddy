package com.example.itemservice.service.Impl;

import com.example.itemservice.dto.ItemDto;
import com.example.itemservice.entity.Item;
import com.example.itemservice.enums.InventoryOperation;
import com.example.itemservice.mapper.ItemMapper;
import com.example.itemservice.repository.ItemRepository;
import com.example.itemservice.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Override
    public Optional<ItemDto> getItemById(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            logger.info("Find item with id: "+ id +" successfully! ");
            return Optional.of(itemMapper.toItemDto(item.get()));
        } else {
            logger.info("No such item with id: "+ id);
        }

        return Optional.empty();
    }

    @Override
    public ItemDto createItem(ItemDto itemDetails) {
        Item item = itemMapper.toItem(itemDetails);

        Item savedItem = itemRepository.save(item);

        return itemMapper.toItemDto(savedItem);
    }

    @Override
    public Optional<ItemDto> updateItem(String id, ItemDto itemDetails) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            item.get().setName(itemDetails.getName());
            item.get().setPrice(itemDetails.getPrice());
            item.get().setDescription(itemDetails.getDescription());
            item.get().setInventory(itemDetails.getInventory());
            item.get().setImageURL(itemDetails.getImageURL());
            item.get().setUpc(itemDetails.getUpc());
            itemRepository.save(item.get());

            return Optional.of(itemMapper.toItemDto(item.get()));
        } else {
            logger.info("No such item with id: "+ id);
        }
        return Optional.empty();
    }

    @Override
    public Optional<ItemDto> deleteItemById(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.deleteById(id);
            return Optional.of(itemMapper.toItemDto(item.get()));
        }
        return Optional.empty();
    }

    @Override
    public List<ItemDto> getAllItems() {
        List<Item> items = itemRepository.findAll();
        List<ItemDto> itemDtos = items.stream()
            .map(itemMapper::toItemDto)
            .collect(Collectors.toList());
        logger.info("all items are: \n" + itemDtos.toString());


        return itemDtos;
    }


    @Override
    public int getInventory(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item.get().getInventory();
        } else {
            logger.info("No such item with id: "+ id);
        }
        return 0;
    }

    @Override
    public int updateInventory(String id, int amount, InventoryOperation operation) {

        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            if (operation == InventoryOperation.SALE) {
                item.get().setInventory(item.get().getInventory() - amount);
            } else if (operation == InventoryOperation.RESTOCK) {
                item.get().setInventory(item.get().getInventory() + amount);
            }

            Item updatedItem = itemRepository.save(item.get());
            return updatedItem.getInventory();
        } else {
            logger.info("No such item with id: "+ id);
        }
        return 0;
    }
}
