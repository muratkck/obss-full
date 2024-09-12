package com.day2.demo.dto.response;


import lombok.Data;

import java.util.List;

@Data
public class AuthResponse {

    String message;
    Long userId;
    List<String> Roles;
    String accessToken;
    String refreshToken;
}