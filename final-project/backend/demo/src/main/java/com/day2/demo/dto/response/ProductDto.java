package com.day2.demo.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private String description;
    private String brand;
    private Double price;
    // Set<String> sellerNames.
    private String sellerNames;
}
