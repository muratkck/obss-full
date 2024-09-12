package com.day2.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_seller")
public class Seller extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

//    @Column(name = "profilePictureUrl")
//    private String profilePictureUrl;

    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String username;

//    @Column(name = "email", unique = true, nullable = false, columnDefinition = "VARCHAR(75)")
//    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "sellers",
                cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        this.products.add(product);
        product.getSellers().add(this);
    }

    public void removeProduct(Product product) {
        this.products.remove(product);
        product.getSellers().remove(this);
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Blacklist> blacklist = new HashSet<>();
}
