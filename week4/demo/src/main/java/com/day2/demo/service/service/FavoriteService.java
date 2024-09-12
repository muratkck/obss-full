package com.day2.demo.service.service;

import com.day2.demo.dto.input.FavoriteInputDto;
import com.day2.demo.dto.response.FavoriteDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.search.FavoriteSearchInputDto;
import com.day2.demo.model.Favorite;
import com.day2.demo.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FavoriteService {

    void saveFavorite(FavoriteInputDto inputDto);

    Page<FavoriteDto> findFavoritesByUserId(FavoriteSearchInputDto searchInputDto);

    void deleteFavorite(FavoriteInputDto inputDto);

    void deleteFavoriteById(Long id);
}
