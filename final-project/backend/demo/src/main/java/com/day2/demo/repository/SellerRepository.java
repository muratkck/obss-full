package com.day2.demo.repository;

import com.day2.demo.dto.search.SellerSearchInputDto;
import com.day2.demo.model.Seller;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByUsername(String username);

    Optional<Seller> findSellerById(Long id);

    void deleteSellerById(Long id);

    @Query("SELECT s " +
            "FROM Seller s " +
            "WHERE (:#{#search.username} IS NULL OR s.username = :#{#search.username}) " +
            "AND LOWER(CONCAT(s.name,' ',s.surname)) LIKE LOWER(:#{#search.fullname}) ")
    Page<Seller> findAllSellersBySearch(@Param("search") SellerSearchInputDto searchInputDto, Pageable pageable);
}
