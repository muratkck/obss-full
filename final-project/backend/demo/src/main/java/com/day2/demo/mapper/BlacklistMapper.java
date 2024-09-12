package com.day2.demo.mapper;

import com.day2.demo.dto.response.BlacklistDto;
import com.day2.demo.model.Blacklist;

public class BlacklistMapper {

    public static BlacklistDto mapEntityToDto(Blacklist blacklist) {
        BlacklistDto blacklistDto = new BlacklistDto();

        blacklistDto.setBlacklistId(blacklist.getId());
        blacklistDto.setSellerUsername(blacklist.getSeller().getUsername());
        blacklistDto.setCreatedAt(blacklist.getCreatedAt());

        return blacklistDto;
    }
}
