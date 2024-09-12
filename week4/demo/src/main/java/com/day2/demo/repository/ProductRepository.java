package com.day2.demo.repository;

import com.day2.demo.dto.search.ProductSearchInputDto;
import com.day2.demo.model.Product;
import com.day2.demo.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

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

}
