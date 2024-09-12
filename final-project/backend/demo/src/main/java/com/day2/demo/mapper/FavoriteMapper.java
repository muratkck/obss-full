package com.day2.demo.mapper;

import com.day2.demo.dto.response.FavoriteDto;
import com.day2.demo.model.Favorite;

public class FavoriteMapper {

    public static FavoriteDto mapEntityToDto(Favorite favorite) {
        FavoriteDto favoriteDto = new FavoriteDto();

        favoriteDto.setFavoriteId(favorite.getId());
        favoriteDto.setId(favorite.getProduct().getId());
        favoriteDto.setUserId(favorite.getUser().getId());
        favoriteDto.setBrand(favorite.getProduct().getBrand());
        favoriteDto.setName(favorite.getProduct().getName());
        favoriteDto.setPrice(favorite.getProduct().getPrice());
        favoriteDto.setCreatedAt(favorite.getCreatedAt());
        return favoriteDto;
    }
}
