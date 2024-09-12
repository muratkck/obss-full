package com.day2.demo.mapper;

import com.day2.demo.dto.input.SellerInputDto;
import com.day2.demo.dto.response.SellerDto;
import com.day2.demo.model.Seller;

public class SellerMapper {

    public static Seller mapInputDtoToEntity(SellerInputDto inputDto){
        Seller seller = new Seller();
        seller.setUsername(inputDto.getUsername());
        seller.setPassword(inputDto.getPassword());
        seller.setName(inputDto.getName());
        seller.setSurname(inputDto.getSurname());
        return seller;
    }

    public static SellerDto mapEntityToDto(Seller seller){
        SellerDto sellerDto = new SellerDto();
        sellerDto.setId(seller.getId());
        sellerDto.setUsername(seller.getUsername());
        sellerDto.setFullName(seller.getName() + " " + seller.getSurname());
        return sellerDto;
    }
}
