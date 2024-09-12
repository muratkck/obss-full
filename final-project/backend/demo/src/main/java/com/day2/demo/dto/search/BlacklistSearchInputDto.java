package com.day2.demo.dto.search;

import lombok.Data;

import java.io.Serializable;

@Data
public class BlacklistSearchInputDto implements Serializable {

    private Long userId;

    private int page = 0;
    private int size = 10;
}
