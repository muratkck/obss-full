package com.day2.demo.service.impl;

import com.day2.demo.dto.input.SellerInputDto;
import com.day2.demo.dto.response.ProductDto;
import com.day2.demo.dto.response.SellerDto;
import com.day2.demo.dto.search.SellerSearchInputDto;
import com.day2.demo.dto.update.SellerUpdateDto;
import com.day2.demo.exception.ConflictException;
import com.day2.demo.mapper.SellerMapper;
import com.day2.demo.mapper.UserMapper;
import com.day2.demo.model.Product;
import com.day2.demo.model.Seller;
import com.day2.demo.repository.SellerRepository;
import com.day2.demo.service.service.SellerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SellerServiceImpl(SellerRepository sellerRepository, PasswordEncoder passwordEncoder) {
        this.sellerRepository = sellerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public SellerDto saveSeller(SellerInputDto inputDto) {

        Seller seller = SellerMapper.mapInputDtoToEntity(inputDto);
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));

        return SellerMapper.mapEntityToDto(sellerRepository.save(seller));
    }

    @Override
    public void saveDefaultSellers() {
        List<Seller> sellers = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            Seller seller = new Seller();
            seller.setUsername("Seller" + (i+1));
            seller.setName("Seller" + (i+1));
            seller.setSurname("Seller" + (i+1));
            seller.setPassword(passwordEncoder.encode("Seller" + (i+1)));
            sellers.add(seller);
        }
        sellerRepository.saveAll(sellers);
    }

    @Override
    public Set<Seller> findAllSellers() {
        return new HashSet<>(sellerRepository.findAll());
    }

    @Override
    public Seller findSellerByUsername(String username) {
        Optional<Seller> seller = sellerRepository.findByUsername(username);
        if (seller.isEmpty()) {
            throw new EntityNotFoundException("Seller with username " + username + " not found");
        }
            return seller.get();
    }

    @Override
    @Transactional
    public void deleteSellerById(Long id) {
        Optional<Seller> sellerOptional = sellerRepository.findSellerById(id);
        if(sellerOptional.isPresent()){

            Seller seller = sellerOptional.get();
//
//            for (Product product : new HashSet<>(seller.getProducts())) {
//                seller.removeProduct(product);
//            }

            sellerRepository.deleteSellerById(id);
            return;
        }
        throw new EntityNotFoundException("Seller with id " + id + " does not exist!");
    }

    @Override
    public Seller findSellerById(Long id) {
        Optional<Seller> seller = sellerRepository.findSellerById(id);
        if (seller.isPresent()) {
            return seller.get();
        }
        throw new EntityNotFoundException("Seller not found");
    }

    @Override
    public Page<SellerDto> findAllSellersBySearch(SellerSearchInputDto searchInputDto) {

        Pageable pageable = PageRequest.of(searchInputDto.getPage(), searchInputDto.getSize());

        Page<Seller> foundSellers = sellerRepository.findAllSellersBySearch(searchInputDto, pageable);

        return foundSellers.map(SellerMapper::mapEntityToDto);

    }

    @Override
    @Transactional
    public SellerDto updateSeller(SellerUpdateDto updateDto) {
        // ID ile mevcut seller'ı bul
        Optional<Seller> optionalSeller = sellerRepository.findSellerById(updateDto.getId());
        if (optionalSeller.isEmpty()) {
            throw new EntityNotFoundException("Seller with id " + updateDto.getId() + " not found");
        }

        Seller existingSeller = optionalSeller.get();
        Optional<Seller> existedUsername = sellerRepository.findByUsername(updateDto.getUsername());

        if (existedUsername.isPresent()) {
            throw new ConflictException("Existed username");
        }

        if (updateDto.getUsername() != null) {
            existingSeller.setUsername(updateDto.getUsername());
        }

        Seller updatedSeller = sellerRepository.save(existingSeller);
        return SellerMapper.mapEntityToDto(updatedSeller);
    }
}
