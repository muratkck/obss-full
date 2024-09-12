package com.day2.demo.service.service;

import com.day2.demo.dto.input.ProductInputDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.search.ProductSearchInputDto;
import com.day2.demo.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    ProductDto saveProduct(ProductInputDto productInputDto);
    // List<ProductDto> findProductsByNameIn(String productName);
    List<ProductDto> findAllProducts();

    // To return the product to the user
    Product findProductById(Long productId);

    void saveDefaultProducts(int count);

    Page<ProductDto> findAllProductsBySearch(ProductSearchInputDto searchInputDto);

    void deleteProductById(Long productId);

    //Product findById( Long productId);
}
