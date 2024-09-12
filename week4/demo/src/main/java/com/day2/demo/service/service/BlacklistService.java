package com.day2.demo.service.service;

import com.day2.demo.dto.input.BlacklistInputDto;
import com.day2.demo.dto.response.BlacklistDto;
import com.day2.demo.dto.search.BlacklistSearchInputDto;
import org.springframework.data.domain.Page;

public interface BlacklistService {

    void saveBlacklist( BlacklistInputDto inputDto);

    Page<BlacklistDto> findBlacklistsByUserId(BlacklistSearchInputDto inputDto);


    void deleteBlacklistById(Long id);
}
