package com.day2.demo.service.impl;

import com.day2.demo.dto.input.ProductInputDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.search.ProductSearchInputDto;
import com.day2.demo.dto.update.ProductUpdateDto;
import com.day2.demo.mapper.ProductMapper;
import com.day2.demo.model.Product;
import com.day2.demo.model.Seller;
import com.day2.demo.repository.ProductRepository;
import com.day2.demo.security.AuthUserDetails;
import com.day2.demo.security.JwtUserDetails;
import com.day2.demo.service.service.BlacklistService;
import com.day2.demo.service.service.ProductService;
import com.day2.demo.service.service.SellerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DecimalFormat;
import java.util.*;

@Slf4j
@Service
//@Transactional
class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    SellerService sellerService;
    BlacklistService blacklistService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, SellerService sellerService, BlacklistService blacklistService) {
        this.productRepository = productRepository;
        this.sellerService = sellerService;
        this.blacklistService = blacklistService;
    }


    @Override
    @Transactional()
    public ProductDto saveProduct(ProductInputDto productInputDto) {

        Seller newSeller =
                sellerService.findSellerByUsername(productInputDto.getSellerUsername());
        productInputDto.setSeller(newSeller);
        Product product = ProductMapper.mapInputDtoToEntity(productInputDto);
        ProductMapper.mapEntityToDto(productRepository.save(product));
        return ProductMapper.mapEntityToDto(productRepository.save(product));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAllProducts(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Product> allProducts = productRepository.findAll(pageable);

        return allProducts.map(ProductMapper::mapEntityToDto);
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
    public void saveDefaultProducts() {

        List<Product> products = new ArrayList<>();

        List<Seller> sellers = sellerService.findAllSellers().stream().toList();

        Random random = new Random();


        for (int i = 0; i < 50; i++) {
            Seller seller = sellers.get(random.nextInt(sellers.size()));
            Product product = new Product();
            product.setName("Product " + (i + 1));
            product.setDescription("DescriptionDescriptionDescriptionDescriptionDescription " + i);
            product.setBrand("Brand " + (i + 1));
            product.setPrice(Double.parseDouble(new DecimalFormat("##.##").format(random.nextDouble(25)*1000)));
            product.setSeller(seller);

            products.add(product);
        }

        productRepository.saveAll(products);

    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDto> findAllProductsBySearch(ProductSearchInputDto searchInputDto) {

        Pageable pageable = PageRequest.of(searchInputDto.getPage(), searchInputDto.getSize());

        log.info(searchInputDto.getName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null
                && (auth.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))
                || auth.getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals("ROLE_ANONYMOUS"))))
        {
            Page<Product> foundProducts = productRepository.findAllProductsBySearch(searchInputDto, pageable);
            return foundProducts.map(ProductMapper::mapEntityToDto);
        }
        else {
            assert auth != null;
            JwtUserDetails user = (JwtUserDetails) auth.getPrincipal();
            Long userId = user.getId();

            Page<Product> foundProducts = productRepository.findAllProductsBySearchExcludeBlacklist(searchInputDto, userId, pageable);
            log.info(foundProducts.getTotalElements() + " products found");
            return foundProducts.map(ProductMapper::mapEntityToDto);
        }
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

    @Override
    @Transactional
    public ProductDto updateProduct(ProductUpdateDto productUpdateDto) {
        // Mevcut ürünü ID ile bul
        Optional<Product> optionalProduct = productRepository.findById(productUpdateDto.getId());
        if (!optionalProduct.isPresent()) {
            throw new EntityNotFoundException("Product with id " + productUpdateDto.getId() + " not found");
        }

        Product existingProduct = optionalProduct.get();

        // Seller değişirse yeni seller'ı bul
        if (productUpdateDto.getSellerUsername() != null) {
            Seller newSeller = sellerService.findSellerByUsername(productUpdateDto.getSellerUsername());
            existingProduct.setSeller(newSeller);
        }

        // Alanları güncelle
        if (productUpdateDto.getName() != null) {
            existingProduct.setName(productUpdateDto.getName());
        }
        if (productUpdateDto.getDescription() != null) {
            existingProduct.setDescription(productUpdateDto.getDescription());
        }
        if (productUpdateDto.getBrand() != null) {
            existingProduct.setBrand(productUpdateDto.getBrand());
        }
        if (productUpdateDto.getPrice() > 0) {
            existingProduct.setPrice(productUpdateDto.getPrice());
        }

        // Güncellenmiş ürünü kaydet
        Product updatedProduct = productRepository.save(existingProduct);
        return ProductMapper.mapEntityToDto(updatedProduct);
    }

}
