package com.day2.demo.controller;

import com.day2.demo.dto.ApiResponse;
import com.day2.demo.dto.input.ProductInputDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.search.ProductSearchInputDto;
import com.day2.demo.dto.update.ProductUpdateDto;
import com.day2.demo.mapper.ProductMapper;
import com.day2.demo.model.Product;
import com.day2.demo.service.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Tag(name = "ProductController", description = "Controls the requests that comes to the /products")
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping
    public ApiResponse<ProductDto> saveProduct(@RequestBody @Valid ProductInputDto productInputDto) {
        return ApiResponse.success(productService.saveProduct(productInputDto));
    }


//    @GetMapping
//    public ApiResponse<List<ProductDto>> findAllProducts() {
//        return ApiResponse.success(productService.findAllProducts());
//    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductDto> findProductById(@PathVariable Long productId) {
        ProductDto productDto = ProductMapper.mapEntityToDto(productService.findProductById(productId));
        return ApiResponse.success(productDto);
    }

    @GetMapping("/page/{page}")
    public ApiResponse<Page<ProductDto>> findAllProducts(@PathVariable int page) {
        return ApiResponse.success(productService.findAllProducts(page));
    }

    @GetMapping
    public ApiResponse<Page<ProductDto>> findAllProductsBySearch(ProductSearchInputDto inputDto) {
        return ApiResponse.success(productService.findAllProductsBySearch(inputDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
        return ApiResponse.success("Product deleted successfully");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ApiResponse<ProductDto> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto) {
        productUpdateDto.setId(id); // DTO'ya ID'yi set et
        ProductDto updatedProduct = productService.updateProduct(productUpdateDto);
        return ApiResponse.success(updatedProduct);
    }
}
