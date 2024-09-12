package com.day2.demo.controller;

import com.day2.demo.dto.ApiResponse;
import com.day2.demo.dto.input.FavoriteInputDto;
import com.day2.demo.dto.response.FavoriteDto;
import com.day2.demo.dto.search.FavoriteSearchInputDto;
import com.day2.demo.service.service.FavoriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Favorites", description = "Controls the requests that comes to the /favorites")
@PreAuthorize("hasAnyRole('USER')")
@RestController
@RequestMapping("/favorites")
class FavoriteController {

    FavoriteService favoriteService;

    @Autowired
    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping
    public ApiResponse<String> saveFavorite(@RequestBody @Valid FavoriteInputDto inputDto) {
        favoriteService.saveFavorite(inputDto);
        return ApiResponse.success("Product added to favorites");
    }

    @GetMapping
    public ApiResponse<Page<FavoriteDto>> findFavoritesByUserId(FavoriteSearchInputDto inputDto) {
        Page<FavoriteDto> favoriteProducts = favoriteService.findFavoritesByUserId(inputDto);
        return ApiResponse.success(favoriteProducts, "Favorites retrieved successfully");
    }

    @DeleteMapping()
    public ApiResponse<String> deleteFavorite(@RequestBody @Valid FavoriteInputDto inputDto) {
        favoriteService.deleteFavorite(inputDto);
        return ApiResponse.success("Product removed from favorites");
    }

    // id or favoriteInputDto object?
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteFavoriteById(@PathVariable Long id) {
        favoriteService.deleteFavoriteById(id);
        return ApiResponse.success("Product removed from favorites");
    }

}




