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
    public ApiResponse<String> saveBlacklist(@RequestBody @Valid BlacklistInputDto inputDto){
        blacklistService.saveBlacklist(inputDto);
        return ApiResponse.success("Seller added to the blacklist");
    }

    @GetMapping
    public ApiResponse<Page<BlacklistDto>> findBlacklistsByUserId(BlacklistSearchInputDto inputDto){
        Page<BlacklistDto> blacklistSellers = blacklistService.findBlacklistsByUserId(inputDto);
        return ApiResponse.success(blacklistSellers, "Sellers retrieved successfully");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteBlacklistById(@PathVariable Long id){
        blacklistService.deleteBlacklistById(id);
        return ApiResponse.success("Seller deleted from the blacklist");
    }
}
