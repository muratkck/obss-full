package com.day2.demo.dto.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String fullName;
}
