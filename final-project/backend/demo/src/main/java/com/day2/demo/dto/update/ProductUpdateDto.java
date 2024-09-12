package com.day2.demo.dto.update;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductUpdateDto implements Serializable {
    private Long id;
    private String name;
    private String description;
    private String brand;
    private double price;
    private String sellerUsername; // Eğer seller değişebilir ise
}