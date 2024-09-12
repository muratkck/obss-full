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
import com.day2.demo.security.JwtUserDetails;
import com.day2.demo.service.service.FavoriteService;
import com.day2.demo.service.service.ProductService;
import com.day2.demo.service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void saveFavorite(Long productId) {
        Long userId = getAuthenticatedUserId();

        if (userId == null) {
            return;
        }

        User user = userService.findUserById(userId);
        Product product = productService.findProductById(productId);

        if (favoriteRepository.existsByUserIdAndProductId(userId, productId)) {
            throw new ConflictException("Product is already in the favorites");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);

        favoriteRepository.save(favorite);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FavoriteDto> findFavoritesByUserId() {
        Long userId = getAuthenticatedUserId();

        if (userId == null) {
            return Page.empty(); // veya uygun bir boş sayfa döndürme yöntemi
        }

        userService.findUserById(userId); // User kontrolü
        Pageable pageable = PageRequest.of(0, 50); // Varsayılan sayfa=0, boyut=50
        Page<Favorite> favorites = favoriteRepository.findFavoritesByUserIdAndNotInBlacklist(userId, pageable);

        return favorites.map(FavoriteMapper::mapEntityToDto);
    }

    @Override
    @Transactional
    public void deleteFavoriteByProductId(Long productId) {
        Long userId = getAuthenticatedUserId();

        if (userId == null) {
            return;
        }

        userService.findUserById(userId);
        productService.findProductById(productId);

        if (favoriteRepository.existsByUserIdAndProductId(userId, productId)) {
            favoriteRepository.deleteByUserIdAndProductId(userId, productId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean checkIfProductIsFavorite(Long productId) {
        Long userId = getAuthenticatedUserId();

        if (userId == null) {
            return false;
        }

        return favoriteRepository.existsByUserIdAndProductId(userId, productId);
    }

    private Long getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getAuthorities().stream().noneMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
            return null;
        }

        JwtUserDetails jwtUser = (JwtUserDetails) auth.getPrincipal();
        return jwtUser.getId();
    }

    /*
    @Override
    public void deleteFavoriteById(Long id) {
        favoriteRepository.deleteFavoriteById(id);
    }
    */
}
