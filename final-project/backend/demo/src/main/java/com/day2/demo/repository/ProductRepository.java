package com.day2.demo.repository;

import com.day2.demo.dto.search.ProductSearchInputDto;
import com.day2.demo.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p " +
            "FROM Product p " +
            "WHERE (:#{#search.name} IS NULL OR LOWER(p.name) LIKE LOWER(:#{#search.name})) ")
    Page<Product> findAllProductsBySearch(
            @Param("search") ProductSearchInputDto search, Pageable pageable
    );
    Optional<Product> findByNameAndDescriptionAndBrand(String name, String description, String brand);

    Optional<Product> findProductById(Long id);

    @Query("SELECT p " +
            "FROM Product p " +
            "LEFT JOIN Blacklist b ON p.seller.id = b.seller.id AND b.user.id = :userId " +
            "WHERE b.seller.id IS NULL " +
            "AND (:#{#searchInputDto.name} IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :#{#searchInputDto.name}, '%')))")
    Page<Product> findAllProductsBySearchExcludeBlacklist(
            @Param("searchInputDto") ProductSearchInputDto searchInputDto,
            @Param("userId") Long userId,
            Pageable pageable
    );
}
