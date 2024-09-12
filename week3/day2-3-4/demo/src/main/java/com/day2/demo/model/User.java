package com.day2.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity implements Serializable {

    @Column(name = "username", unique = true, nullable = false, columnDefinition = "VARCHAR(20)")
    private String username;

    @Column(name = "password", nullable = false, columnDefinition = "VARCHAR(255)")
    private String password;

    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(20)")
    private String name;

    @Column(name = "surname", nullable = false, columnDefinition = "VARCHAR(20)")
    private String surname;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserLogin> logins = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "users",
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Role> roles = new HashSet<>();

}
