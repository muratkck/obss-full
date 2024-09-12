package com.day2.demo.dto.response;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FavoriteDto implements Serializable {

    private Long favoriteId;
    private Long id;// of product
    private Long userId;
    private String brand;
    private String name;
    private Double price;
    private LocalDateTime createdAt;
//    private String productImage;
}
