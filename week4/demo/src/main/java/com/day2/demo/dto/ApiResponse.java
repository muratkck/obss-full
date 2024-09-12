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
    private String code;

    @JsonIgnore
    private ApiResponseCode codeEnum;

    public ApiResponse(T data, String message, ApiResponseCode codeEnum) {
        this.data = data;
        this.message = message;
        this.codeEnum = codeEnum;
        this.code = codeEnum.getCode();
    }

    public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<>(data, message, ApiResponseCode.OK);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(data, "Operation successful", ApiResponseCode.OK);
    }

//    public static ApiResponse<?> success(String message) {
//        return new ApiResponse<>(null, message, ApiResponseCode.OK);
//    }

    public static <T> ApiResponse<T> success(String message) {
        return new ApiResponse<>(null, message, ApiResponseCode.OK);
    }


    public static ApiResponse<?> error(String message, ApiResponseCode code) {
        return new ApiResponse<>(null, message, code);
    }

    public static ApiResponse<?> error(String message) {
        // BAD_REQUEST is temporary
        return new ApiResponse<>(null, message, ApiResponseCode.BAD_REQUEST);
    }

    public static ApiResponse<?> ofCode(ApiResponseCode code, String message) {
        return new ApiResponse<>(null, message, code);
    }
    /*
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

    public static ApiResponse<?> of(ApiResponseCode code) {
        return new ApiResponse<>(null, null, code);
    }
    */

}
