package com.day2.demo.controller;

import com.day2.demo.dto.ApiResponse;
import com.day2.demo.dto.input.SellerInputDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.response.SellerDto;
import com.day2.demo.dto.search.SellerSearchInputDto;
import com.day2.demo.dto.update.ProductUpdateDto;
import com.day2.demo.dto.update.SellerUpdateDto;
import com.day2.demo.mapper.ProductMapper;
import com.day2.demo.mapper.SellerMapper;
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

    @GetMapping("/{id}")
    public ApiResponse<SellerDto> findSellerById(@PathVariable Long id) {
        SellerDto sellerDto = SellerMapper.mapEntityToDto(sellerService.findSellerById(id));
        return ApiResponse.success(sellerDto);
    }

    @GetMapping
    public ApiResponse<Page<SellerDto>> findAllSellersBySearch(SellerSearchInputDto searchInputDto){
        return ApiResponse.success(sellerService.findAllSellersBySearch(searchInputDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteSeller(@PathVariable Long id){
        sellerService.deleteSellerById(id);
        return ApiResponse.success("Seller deleted successfully");
    }

    @PutMapping("/{id}")
    public ApiResponse<SellerDto> updateSeller(@PathVariable Long id, @RequestBody SellerUpdateDto updateDto) {
        updateDto.setId(id); // DTO'ya ID'yi set et
        SellerDto updatedProduct = sellerService.updateSeller(updateDto);
        return ApiResponse.success(updatedProduct);
    }
}
