package com.example.itemservice.repository;

import com.example.itemservice.entity.Item;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */
@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    // ?0 是占位符，表示方法的第一个参数（从0开始计数）。
//    @Query("{name:'?0'}")
//    Item findItemByName(String name);
}
