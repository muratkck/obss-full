package com.day2.demo.dto.input;

import com.day2.demo.model.Seller;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class ProductInputDto implements Serializable {

    @NotNull
    @Size(min = 5, max = 25, message = "Product name should be have min 5 max 25 characters!")
    @ToString.Include
    private String name;

    @NotNull
    @ToString.Include
    @Size(min = 20, max = 300, message = "Product definition should have at least 20, and at most 300 characters!")
    private String description;

    @NotNull
    @ToString.Include
    @Size(min = 1, max = 50, message = "Brand name should have at least 1 character!")
    private String brand;

    @NotNull
    @ToString.Include
    @Positive(message = "Price must be greater than zero!")
    private Double price;

    @NotNull
    @Size(min = 2, max = 50, message = "Seller name should have at least 2 characters!")
    private String sellerUsername;
}

//    private double discount;
//    private Integer quantity;
//    private double specialPrice;

//@Min(value = 1, message = "{com.eCommerceProject.Min.stock.message}")
//    @Min(value = 1, message = "Minimum Stock mesajı")
//    @Column(name = "stock")
//    private int stock;

//    @Column(name = "image")
//    private String productImageUrl;