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

}
