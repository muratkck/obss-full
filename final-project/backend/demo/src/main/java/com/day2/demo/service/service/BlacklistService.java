package com.day2.demo.service.service;

import com.day2.demo.dto.response.BlacklistDto;
import com.day2.demo.dto.search.BlacklistSearchInputDto;
import org.springframework.data.domain.Page;

public interface BlacklistService {

    void saveBlacklist(String sellerUsername);

    Page<BlacklistDto> findBlacklistsByUserId();


    void deleteBlacklistBySellerUsername(String sellerUsername);

    Boolean checkIfSellerIsBlacklisted(String sellerUsername);
}
