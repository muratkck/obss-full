package com.day2.demo.service.impl;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import com.day2.demo.model.RefreshToken;
import com.day2.demo.model.User;
import com.day2.demo.repository.RefreshTokenRepository;
import com.day2.demo.service.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${refresh.token.expires.in}")
    Long expireSeconds;

    private RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public String createRefreshToken(User user) {
        RefreshToken token = refreshTokenRepository.findByUserId(user.getId());
        if(token == null) {
            token =	new RefreshToken();
            token.setUser(user);
        }
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Date.from(Instant.now().plusSeconds(expireSeconds)));
        refreshTokenRepository.save(token);
        return token.getToken();
    }

    @Override
    public boolean isRefreshExpired(RefreshToken token) {
        return token.getExpiryDate().before(new Date());
    }

    @Override
    public RefreshToken getByUser(Long userId) {
        return refreshTokenRepository.findByUserId(userId);
    }
}
