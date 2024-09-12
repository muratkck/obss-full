package com.day2.demo.service.service;

import com.day2.demo.model.RefreshToken;
import com.day2.demo.model.User;

public interface RefreshTokenService {
    String createRefreshToken(User user);

    boolean isRefreshExpired(RefreshToken token);

    RefreshToken getByUser(Long userId);
}
