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

    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller",
                cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Blacklist> blacklist = new HashSet<>();
}
