package com.day2.demo.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BlacklistDto implements Serializable {

    private Long blacklistId;
    private Long userId;
    private Long sellerId;
    private String sellerUsername;
    private LocalDateTime createdAt;

}
