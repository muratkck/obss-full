package com.day2.demo.dto.input;

import lombok.Data;

@Data
public class RefreshRequestInputDto {
    Long userId;
    String refreshToken;
}
