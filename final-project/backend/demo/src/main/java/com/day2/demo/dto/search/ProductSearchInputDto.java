package com.day2.demo.dto.search;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

@Data
public class ProductSearchInputDto implements Serializable {

    private String name;
    //private String description;
    private int page = 0;

    private int size = 10;

    public String getName() {
        return StringUtils.isNotBlank(name) ? "%" + name + "%" : "%%";
    }

}
