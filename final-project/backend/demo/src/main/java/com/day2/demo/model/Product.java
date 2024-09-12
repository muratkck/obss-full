package com.day2.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity implements Serializable {

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(25)")
    //@EqualsAndHashCode.Include
    private String name;

//    private String image;
    @Column(name = "description", nullable = false, columnDefinition = "VARCHAR(300)")
    private String description;

    @Column(name = "brand", nullable = false, columnDefinition = "VARCHAR(50)")
    //@EqualsAndHashCode.Include
    private String brand;

    @Column(name = "price", nullable = false, columnDefinition = "DOUBLE")
//    @EqualsAndHashCode.Include
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Favorite> favorites;

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


//      Temporarily String for seller field