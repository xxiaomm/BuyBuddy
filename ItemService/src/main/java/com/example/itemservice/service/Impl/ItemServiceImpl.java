package com.example.itemservice.service.Impl;

import com.example.itemservice.entity.Item;
import com.example.itemservice.repository.ItemRepository;
import com.example.itemservice.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
    @Override
    public Optional<Item> getItemById(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            logger.info("Find item with id: "+ id +" successfully! ");
        } else {
            logger.info("No such item with id: "+ id);
        }

        return item;
    }

    @Override
    public Item createItem(Item itemDetails) {
        Item item = new Item();
        item.setName(itemDetails.getName());
        item.setPrice(itemDetails.getPrice());
        item.setDescription(itemDetails.getDescription());
        item.setInventory(itemDetails.getInventory());
        item.setImageURL(itemDetails.getImageURL());
//        item.setUpc(itemDetails.getUpc());

        Item savedItem = itemRepository.save(item);

        return savedItem;
    }

    @Override
    public Optional<Item> updateItem(String id, Item itemDetails) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            item.get().setName(itemDetails.getName());
            item.get().setPrice(itemDetails.getPrice());
            item.get().setDescription(itemDetails.getDescription());
            item.get().setInventory(itemDetails.getInventory());
            item.get().setImageURL(itemDetails.getImageURL());
//            item.get().setUpc(itemDetails.getUpc());
        } else {
            logger.info("No such item with id: "+ id);
        }
        return item;
    }

    @Override
    public Optional<Item> deleteItemById(String id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            itemRepository.deleteById(id);
        }
        return item;
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> items = itemRepository.findAll();
        logger.info("all items are: \n"+items.toString());
        return items;
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
    public int updateInventory(String id, int soldAmount) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            item.get().setInventory(item.get().getInventory() - soldAmount);
            return item.get().getInventory();
        } else {
            logger.info("No such item with id: "+ id);
        }
        return 0;
    }
}
