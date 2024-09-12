package com.day2.demo.dto.search;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class SellerSearchInputDto implements Serializable {

    private String username;
    private String fullname;
//    private String sellerEmail;

    private int page = 0;

    private int size = 2;

    public String getFullname() {
        return StringUtils.isNotBlank(fullname) ? "%" + fullname + "%" : "%%";
    }
}
