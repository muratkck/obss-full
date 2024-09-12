package com.day2.demo.service.impl;

import com.day2.demo.dto.input.FavoriteInputDto;
import com.day2.demo.dto.response.FavoriteDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.search.FavoriteSearchInputDto;
import com.day2.demo.exception.ConflictException;
import com.day2.demo.mapper.FavoriteMapper;
import com.day2.demo.mapper.ProductMapper;
import com.day2.demo.model.Favorite;
import com.day2.demo.model.Product;
import com.day2.demo.model.User;
import com.day2.demo.repository.FavoriteRepository;
import com.day2.demo.repository.ProductRepository;
import com.day2.demo.repository.UserRepository;
import com.day2.demo.service.service.FavoriteService;
import com.day2.demo.service.service.ProductService;
import com.day2.demo.service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    FavoriteServiceImpl(FavoriteRepository favoriteRepository, UserService userService, ProductService productService) {
        this.favoriteRepository = favoriteRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void saveFavorite(FavoriteInputDto inputDto) {

        User user = userService.findUserById(inputDto.getUserId());
        Product product = productService.findProductById(inputDto.getProductId());

        // Eğer favori zaten mevcutsa, tekrardan eklemeye gerek yok
        if (favoriteRepository.existsByUserIdAndProductId(inputDto.getUserId(), inputDto.getProductId())) {
            throw new ConflictException("Product is already in the favorites");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);


        favoriteRepository.save(favorite);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FavoriteDto> findFavoritesByUserId(FavoriteSearchInputDto searchInputDto) {

        userService.findUserById(searchInputDto.getUserId());
        Pageable pageable = PageRequest.of(searchInputDto.getPage(), searchInputDto.getSize());
        Page<Favorite> favorites = favoriteRepository.findFavoritesByUserId(searchInputDto.getUserId(), pageable);

        return favorites.map(FavoriteMapper::mapEntityToDto);
    }

    @Override
    public void deleteFavorite(FavoriteInputDto inputDto) {
        // isUserExisit()
        User user = userService.findUserById(inputDto.getUserId());
        // isProductExist()
        Product product = productService.findProductById(inputDto.getProductId());
        // isFavoriteExist()
        Favorite favorite = favoriteRepository.findByUserIdAndProductId(inputDto.getUserId(), inputDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Favorite not found"));

        favoriteRepository.delete(favorite);
//        deleteFavoriteById(favorite.getId());
    }

    @Override
    public void deleteFavoriteById(Long id) {
        favoriteRepository.deleteFavoriteById(id);
    }
}
