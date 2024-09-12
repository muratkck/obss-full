package com.day2.demo.dto;

import com.day2.demo.constant.ApiResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> implements Serializable {

    private T data;
    private String message;

    @JsonIgnore
    private ApiResponseCode codeEnum;

    private String code;

    public ApiResponse(T data, String message, ApiResponseCode codeEnum) {
        this.data = data;
        this.message = message;
        this.codeEnum = codeEnum;
        this.code = codeEnum.getCode();
    }

    public static <T> ApiResponse<T> of(T data) {
        return new ApiResponse<>(data, null, ApiResponseCode.OK);
    }

    public static ApiResponse<?> of(String message) {
        return new ApiResponse<>(null, message, ApiResponseCode.ERROR);
    }

    public static ApiResponse<?> ofCode(ApiResponseCode code) {
        return new ApiResponse<>(null, null, code);
    }
}
