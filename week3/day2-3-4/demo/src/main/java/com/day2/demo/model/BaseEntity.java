package com.day2.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsExclude;

import java.io.Serializable;
import java.time.LocalDateTime;

//@Data  -> dikkatli kullanmak gerekir: equals, hashcode, toString metotları için özellikle
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
// equalsAndHashcode exclude with annotations -> SEARCH
public abstract class BaseEntity implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    protected void onUpdate() {
        setUpdatedAt(LocalDateTime.now());
    }

}
