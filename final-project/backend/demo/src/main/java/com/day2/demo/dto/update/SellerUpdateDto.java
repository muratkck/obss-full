package com.day2.demo.dto.update;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class SellerUpdateDto implements Serializable {

    private Long id;
    @Getter
    private String username;
}
