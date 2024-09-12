package com.day2.demo.service.service;

import com.day2.demo.dto.input.SellerInputDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.response.SellerDto;
import com.day2.demo.dto.search.SellerSearchInputDto;
import com.day2.demo.dto.update.SellerUpdateDto;
import com.day2.demo.model.Seller;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;

import java.util.Optional;
import java.util.Set;

public interface SellerService {

    SellerDto saveSeller(SellerInputDto inputDto);
    void saveDefaultSellers();
    Set<Seller> findAllSellers();
    Seller findSellerByUsername(String username);

    void deleteSellerById(Long id);

    Seller findSellerById(Long id);

    Page<SellerDto> findAllSellersBySearch(SellerSearchInputDto searchInputDto);

    SellerDto updateSeller(SellerUpdateDto updateDto);
}
