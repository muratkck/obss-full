package com.day2.demo.mapper;

import com.day2.demo.dto.input.ProductInputDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.model.Product;
import com.day2.demo.model.Seller;
import java.util.List;

public class ProductMapper {
    public static Product mapInputDtoToEntity(ProductInputDto inputDto) {
        Product product = new Product();
        product.setName(inputDto.getName());
        product.setPrice(inputDto.getPrice());
        product.setDescription(inputDto.getDescription());
        product.setBrand(inputDto.getBrand());
        product.setSeller(inputDto.getSeller());

        return product;
    }

    public static ProductDto mapEntityToDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setBrand(product.getBrand());
        productDto.setPrice(product.getPrice());
        productDto.setSellerNames(product.getSeller().getUsername());

        return productDto;
    }
}
