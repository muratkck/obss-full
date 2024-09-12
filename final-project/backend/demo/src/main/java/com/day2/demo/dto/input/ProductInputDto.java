package com.day2.demo.dto.input;

import com.day2.demo.model.Seller;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private Seller seller;
}