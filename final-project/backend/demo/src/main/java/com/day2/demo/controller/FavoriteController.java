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
    public ApiResponse<String> saveFavorite(@RequestParam Long productId) {
        favoriteService.saveFavorite(productId);
        return ApiResponse.success("Product added to favorites");
    }

    @GetMapping
    public ApiResponse<Page<FavoriteDto>> findFavoritesByUserId() {
        Page<FavoriteDto> favoriteProducts = favoriteService.findFavoritesByUserId();
        return ApiResponse.success(favoriteProducts, "Favorites retrieved successfully");
    }

    @GetMapping("/check")
    public ApiResponse<Boolean> checkFavorite(@RequestParam Long productId) {
        return ApiResponse.success(favoriteService.checkIfProductIsFavorite(productId));
    }

//    @DeleteMapping()
//    public ApiResponse<String> deleteFavorite(@RequestBody @Valid FavoriteInputDto inputDto) {
//        favoriteService.deleteFavorite(inputDto);
//        return ApiResponse.success("Product removed from favorites");
//    }

    // id or favoriteInputDto object?
    @DeleteMapping
    public ApiResponse<String> deleteFavoriteByProductId(@RequestParam Long productId) {
        favoriteService.deleteFavoriteByProductId(productId);
        return ApiResponse.success("Product removed from favorites");
    }

}




