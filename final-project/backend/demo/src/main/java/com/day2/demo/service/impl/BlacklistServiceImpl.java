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
import com.day2.demo.security.JwtUserDetails;
import com.day2.demo.service.service.BlacklistService;
import com.day2.demo.service.service.SellerService;
import com.day2.demo.service.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public void saveBlacklist(String sellerUsername) {

        Long userId = getAuthenticatedUserId();

        if (userId == null) {
            return;
        }

        User user = userService.findUserById(userId);
        Seller seller = sellerService.findSellerByUsername(sellerUsername);

        if (blacklistRepository.existsByUserIdAndSellerId(userId, seller.getId())) {
            throw new ConflictException("Seller is already blacklisted");
        }

        Blacklist blacklist = new Blacklist();
        blacklist.setSeller(seller);
        blacklist.setUser(user);

        blacklistRepository.save(blacklist);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BlacklistDto> findBlacklistsByUserId() {

        Long userId = getAuthenticatedUserId();

        if (userId == null) {
            return Page.empty(); // veya uygun bir boş sayfa döndürme yöntemi
        }
        userService.findUserById(userId);

        Pageable pageable = PageRequest.of(0, 50);
        Page<Blacklist> blacklists = blacklistRepository.findBlacklistsByUserId(userId, pageable);

        return blacklists.map(BlacklistMapper::mapEntityToDto);
    }

    @Override
    public void deleteBlacklistBySellerUsername(String sellerUsername) {
        Long userId = getAuthenticatedUserId();

        if (userId == null) {
            return;
        }

        User user = userService.findUserById(userId);
        Seller seller = sellerService.findSellerByUsername(sellerUsername);

        if (blacklistRepository.existsByUserIdAndSellerId(userId, seller.getId())) {
            blacklistRepository.deleteByUserIdAndSellerId(userId, seller.getId());
        }
    }

    @Override
    public Boolean checkIfSellerIsBlacklisted(String sellerUsername) {
        Long userId = getAuthenticatedUserId();

        if (userId == null) {
            return false;
        }

        Seller seller = sellerService.findSellerByUsername(sellerUsername);
        return blacklistRepository.existsByUserIdAndSellerId(userId, seller.getId());

    }


    private Long getAuthenticatedUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || auth.getAuthorities().stream().noneMatch(authority -> authority.getAuthority().equals("ROLE_USER"))) {
            return null;
        }

        JwtUserDetails jwtUser = (JwtUserDetails) auth.getPrincipal();
        return jwtUser.getId();
    }

}
