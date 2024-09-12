package com.day2.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user_login")
public class UserLogin extends BaseEntity implements Serializable {

    @Column(name = "user_agent", nullable = false)
    private String userAgent;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;  // mappedBy = "user" ile aynı isimde olmalı!

}
