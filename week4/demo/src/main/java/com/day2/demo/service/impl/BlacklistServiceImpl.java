package com.day2.demo.service.impl;

import com.day2.demo.dto.input.BlacklistInputDto;
import com.day2.demo.dto.response.BlacklistDto;
import com.day2.demo.dto.search.BlacklistSearchInputDto;
import com.day2.demo.exception.ConflictException;
import com.day2.demo.mapper.BlacklistMapper;
import com.day2.demo.model.Blacklist;
import com.day2.demo.model.Seller;
import com.day2.demo.model.User;
import com.day2.demo.repository.BlacklistRepository;
import com.day2.demo.repository.SellerRepository;
import com.day2.demo.service.service.BlacklistService;
import com.day2.demo.service.service.SellerService;
import com.day2.demo.service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BlacklistServiceImpl implements BlacklistService {

    private final BlacklistRepository blacklistRepository;
    private final UserService userService;
    private final SellerService sellerService;

    @Autowired
    BlacklistServiceImpl(BlacklistRepository blacklistRepository, SellerService sellerService, UserService userService, SellerRepository sellerRepository) {
        this.blacklistRepository = blacklistRepository;
        this.userService = userService;
        this.sellerService = sellerService;
    }

    @Override
    public void saveBlacklist(BlacklistInputDto inputDto) {

        User user = userService.findUserById(inputDto.getUserId());
        Seller seller = sellerService.findSellerById(inputDto.getSellerId());

        if (blacklistRepository.existsByUserIdAndSellerId(inputDto.getUserId(), inputDto.getSellerId())){
            throw new ConflictException("Seller is already blacklisted");
        }

        Blacklist blacklist = new Blacklist();
        blacklist.setSeller(seller);
        blacklist.setUser(user);

        blacklistRepository.save(blacklist);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlacklistDto> findBlacklistsByUserId(BlacklistSearchInputDto searchInputDto) {
        userService.findUserById(searchInputDto.getUserId());
        Pageable pageable = PageRequest.of(searchInputDto.getPage(), searchInputDto.getSize());
        Page<Blacklist> blacklists = blacklistRepository.findBlacklistsByUserId(searchInputDto.getUserId(), pageable);

        return blacklists.map(BlacklistMapper::mapEntityToDto);
    }

    @Override
    public void deleteBlacklistById(Long id) {
        blacklistRepository.findBlacklistById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blacklist not found"));

        blacklistRepository.deleteBlacklistById(id);
    }


}
