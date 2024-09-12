package com.day2.demo.dto.response;

import lombok.Data;
import org.w3c.dom.stylesheets.LinkStyle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class UserDto implements Serializable {

    private Long id;
    private String username;
    private String fullName;
    private List<String> roles;
}
