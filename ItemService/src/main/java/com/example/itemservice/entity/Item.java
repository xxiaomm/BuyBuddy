package com.example.itemservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Create 08/2024
 * @Author xiao
 * @Description
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Items")
public class Item {
    @Id
    private String id;
    private String name;
    private double price;
    private String description;
    private String upc; // Universal Product Code
    private String imageURL;
    private int inventory;

}
