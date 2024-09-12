package com.day2.demo.controller;

import com.day2.demo.dto.ApiResponse;
import com.day2.demo.dto.input.BlacklistInputDto;
import com.day2.demo.dto.response.BlacklistDto;
import com.day2.demo.dto.search.BlacklistSearchInputDto;
import com.day2.demo.service.service.BlacklistService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Blacklists", description = "Controls the requests that comes to the /blacklists")
@PreAuthorize("hasAnyRole('USER')")
@RestController
@RequestMapping("/blacklists")
class BlacklistController {

    BlacklistService blacklistService;

    @Autowired
    public BlacklistController(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
    }

    @PostMapping
    public ApiResponse<String> saveBlacklist(@RequestParam String sellerUsername){
        blacklistService.saveBlacklist(sellerUsername);
        return ApiResponse.success("Seller added to the blacklist");
    }

    @GetMapping
    public ApiResponse<Page<BlacklistDto>> findBlacklistsByUserId(){
        Page<BlacklistDto> blacklistSellers = blacklistService.findBlacklistsByUserId();
        return ApiResponse.success(blacklistSellers, "Sellers retrieved successfully");
    }

    @GetMapping("/check")
    public ApiResponse<Boolean> checkBlacklist(@RequestParam String sellerUsername) {
        return ApiResponse.success(blacklistService.checkIfSellerIsBlacklisted(sellerUsername));
    }

    @DeleteMapping
    public ApiResponse<String> deleteBlacklistBySellerUsername(@RequestParam String sellerUsername){
        blacklistService.deleteBlacklistBySellerUsername(sellerUsername);
        return ApiResponse.success("Seller deleted from the blacklist");
    }
}
