/*
package com.day2.demo.exception;

import com.day2.demo.dto.ApiResponse;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> exception(MethodArgumentNotValidException ex) {
        return ResponseEntity.badRequest().body(
                ApiResponse.error(ex.getBindingResult().getAllErrors()
                        .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.joining("\n"))));
    }
}
*/
package com.day2.demo.exception;

import com.day2.demo.dto.ApiResponse;
import com.day2.demo.constant.ApiResponseCode;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors()
                .stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("\n"));

        return ResponseEntity.badRequest().body(
                ApiResponse.error(errorMessage, ApiResponseCode.BAD_REQUEST));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleEntityNotFoundException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ApiResponse.error(ex.getMessage(), ApiResponseCode.NOT_FOUND));
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiResponse<?>> handleConflictException(ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ApiResponse.error(ex.getMessage(), ApiResponseCode.CONFLICT)
        );
    }

//    @ExceptionHandler(UnauthorizedAccessException.class) // Example for custom exception
//    public ResponseEntity<ApiResponse<?>> handleUnauthorizedAccessException(UnauthorizedAccessException ex) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
//                ApiResponse.error(ex.getMessage(), ApiResponseCode.UNAUTHORIZED));
//    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleAccessDeniedException(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                ApiResponse.error(ex.getMessage(), ApiResponseCode.FORBIDDEN)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneralException(Exception ex) {
//        log.info("UNEXPECTED ERROR CLASS NAME:  " + ex.getClass().getName());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ApiResponse.error("An unexpected error occurred: " + ex.getClass().getName(), ApiResponseCode.INTERNAL_SERVER_ERROR));
    }
}
