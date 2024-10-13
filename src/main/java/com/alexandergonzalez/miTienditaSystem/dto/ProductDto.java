package com.alexandergonzalez.miTienditaSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDto {
    private String id;
    private String name;
    private double price;
    private String details;

    public ProductDto(String name, double price, String details) {
        this.name = name;
        this.price = price;
        this.details = details;
    }
}
