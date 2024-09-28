package com.alexandergonzalez.miTienditaSystem.models.product;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    private String id;
    private String name;
    private double price;
    private String details;

    public Product() {
    }

    public Product(ProductDto productDto){
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.details = productDto.getDetails();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void update(ProductDto productDto){
        this.name = productDto.getName();
        this.price = productDto.getPrice();
        this.details = productDto.getDetails();
    }
}
