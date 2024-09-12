package com.day2.demo.dto.response;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class FavoriteDto implements Serializable {

    private Long favoriteId;
    private Long productId;
    private Long userId;
    private String productBrand;
    private String productName;
    private Double productPrice;
    private LocalDateTime createdAt;
//    private String productImage;
}
