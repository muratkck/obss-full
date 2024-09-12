package com.day2.demo.mapper;

import com.day2.demo.dto.response.FavoriteDto;
import com.day2.demo.model.Favorite;

public class FavoriteMapper {

    public static FavoriteDto mapEntityToDto(Favorite favorite) {
        FavoriteDto favoriteDto = new FavoriteDto();

        favoriteDto.setProductId(favorite.getProduct().getId());
        favoriteDto.setUserId(favorite.getUser().getId());
        favoriteDto.setProductBrand(favorite.getProduct().getBrand());
        favoriteDto.setProductName(favorite.getProduct().getName());
        favoriteDto.setProductPrice(favorite.getProduct().getPrice());
        favoriteDto.setCreatedAt(favorite.getCreatedAt());
        return favoriteDto;
    }
}
