package com.alexandergonzalez.miTienditaSystem.models.product;

import java.util.Date;

public class ProductDto {
    private String name;
    private double price;
    private String details;

    public ProductDto() {
    }

    public ProductDto(String name, double price, String details) {
        this.name = name;
        this.price = price;
        this.details = details;
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
}
