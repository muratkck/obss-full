package com.day2.demo.constant;

public enum ApiResponseCode {

    OK("200"),
    CREATED("201"),
    NO_CONTENT("204"),
    BAD_REQUEST("400"),
    UNAUTHORIZED("401"),
    ERROR("402"),
    FORBIDDEN("403"),
    NOT_FOUND("404"),
    CONFLICT("409"),
    INTERNAL_SERVER_ERROR("500");

    private final String code;

    ApiResponseCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
