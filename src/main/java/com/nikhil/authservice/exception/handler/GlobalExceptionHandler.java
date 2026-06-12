package com.nikhil.authservice.exception.handler;

import com.nikhil.authservice.common.response.ApiResponse;
import com.nikhil.authservice.exception.custom.EmailAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ApiResponse> EmailAlreadyExistsExceptionHandler(EmailAlreadyExistsException e){
        String message = "Email is already present.";
        ApiResponse apiResponse = new ApiResponse<>(false,message,null, LocalDateTime.now());

        return new ResponseEntity<>(apiResponse, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse>MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        // collecting validation exception message
        String message = Objects.requireNonNull(e.getFieldError()).getDefaultMessage();

        ApiResponse apiResponse = new ApiResponse<>(false,message,null, LocalDateTime.now());

        return new ResponseEntity<>(apiResponse , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse> ExceptionHandler(Exception e){
        String message = "Unexpected error occurred";
        ApiResponse apiResponse = new ApiResponse<>(false,message,null, LocalDateTime.now());

        return new ResponseEntity<>(apiResponse , HttpStatus.BAD_REQUEST);
    }
}
