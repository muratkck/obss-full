package com.day2.demo.constant;

public enum ApiResponseCode {

    OK("200"),
    ERROR("400");

    private final String code;

    ApiResponseCode(String code) {
        this.code = code;
    }

    public String getCode(){
        return code;
    }
}
