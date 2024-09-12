package com.day2.demo.controller;

import com.day2.demo.dto.ApiResponse;
import com.day2.demo.dto.input.SellerInputDto;
import com.day2.demo.dto.response.SellerDto;
import com.day2.demo.dto.search.SellerSearchInputDto;
import com.day2.demo.service.service.SellerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Sellers", description = "Controls the requests that comes to the /sellers")
@RestController
@RequestMapping("/sellers")
@PreAuthorize("hasRole('ADMIN')")
//@PreAuthorize("hasAnyRole('ADMIN', 'SELLER')")
public class SellerController {

    private final SellerService sellerService;

    @Autowired
    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @PostMapping
    public ApiResponse<SellerDto> saveSeller(@Valid @RequestBody SellerInputDto inputDto){
        return ApiResponse.success(sellerService.saveSeller(inputDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteSeller(@PathVariable Long id){
        sellerService.deleteSellerById(id);
        return ApiResponse.success("Seller deleted successfully");
    }

    @GetMapping
    public ApiResponse<Page<SellerDto>> findAllSellersBySearch(SellerSearchInputDto searchInputDto){
        return ApiResponse.success(sellerService.findAllSellersBySearch(searchInputDto));
    }


}
