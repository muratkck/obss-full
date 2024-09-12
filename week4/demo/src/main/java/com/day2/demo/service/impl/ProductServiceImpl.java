package com.day2.demo.service.impl;

import com.day2.demo.dto.input.ProductInputDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.search.ProductSearchInputDto;
import com.day2.demo.mapper.ProductMapper;
import com.day2.demo.model.Product;
import com.day2.demo.model.Seller;
import com.day2.demo.repository.ProductRepository;
import com.day2.demo.service.service.ProductService;
import com.day2.demo.service.service.SellerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
//@Transactional
class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    SellerService sellerService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SellerService sellerService) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
    }


    @Override
    public ProductDto saveProduct(ProductInputDto productInputDto) {
        Optional<Product> existingProduct = productRepository.findByNameAndDescriptionAndBrand(
                productInputDto.getName(),
                productInputDto.getDescription(),
                productInputDto.getBrand()
        );

        // product equals metodu ile eşit mi diye kontrol edip ona göre davranmak?

        Seller newSeller = sellerService.findSellerByUsername(productInputDto.getSellerUsername());

        Product product;
        product = existingProduct.orElseGet(() -> ProductMapper.mapInputDtoToEntity(productInputDto));
        // If seller already exist(for now no duplicate entity occurs there is no exception here!)
        product.getSellers().add(newSeller);
        newSeller.getProducts().add(product);
        return ProductMapper.mapEntityToDto(productRepository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> findAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts.stream().map(ProductMapper::mapEntityToDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Product findProductById(Long productId) {

        Optional<Product> product = productRepository.findProductById(productId);
        if(product.isPresent()){
            return product.get();
        }
        throw new EntityNotFoundException("Product with id " + productId + " not found");
    }

    @Override
    @Transactional
    public void saveDefaultProducts(int count) {

        List<Product> products = new ArrayList<>();

        Set<Seller> sellers = sellerService.findAllSellers();
        Seller seller1 = sellers.iterator().next();

        for (int i = 0; i < count; i++) {

            Product product = new Product();
            product.setName("Product " + i);
            product.setDescription("DescriptionDescriptionDescriptionDescriptionDescription " + i);
            product.setBrand("Brand " + i);
            product.setPrice(i*1000.0);
            product.getSellers().add(seller1);
            seller1.getProducts().add(product);

            products.add(product);
        }

        productRepository.saveAll(products);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAllProductsBySearch(ProductSearchInputDto searchInputDto) {
        /*
        // Authority'ye göre product search edilecek: User-blacklist seller'ın ürünlerini görmeyecek!
        Eğer search yapan client'ın rolü admin ise herhangi bir filtreleme olmadan ürünler'de arama yapılacak.
        Fakat rolü user ise, arama yapılırken sorgu userin blacklist'inde bulunan seller'ların ürünlerini göstermeyecek şekilde
        ürünleri döndürecek.
        var i = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for(GrantedAuthority authority : i){
            log.info("authority: {}", authority.getAuthority());
        }
        */
        SecurityContext context = SecurityContextHolder.getContext();
        log.info("Searcher username: " + context.getAuthentication().getAuthorities());

        Pageable pageable = PageRequest.of(searchInputDto.getPage(), searchInputDto.getSize());

        Page<Product> foundProducts = productRepository.findAllProductsBySearch(searchInputDto, pageable);

        return foundProducts.map(ProductMapper::mapEntityToDto);
    }

    @Override
    @Transactional
    public void deleteProductById(Long productId) {
        Optional<Product> product= productRepository.findById(productId);
        if(product.isPresent()){
            productRepository.deleteById(productId);
            return;
        }
        throw new EntityNotFoundException("Product with id " + productId + " not found");
    }

}
