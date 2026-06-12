package com.nikhil.authservice.auth.controller;

import com.nikhil.authservice.auth.dto.request.RegisterUserRequest;
import com.nikhil.authservice.auth.dto.response.RegisterUserResponse;
import com.nikhil.authservice.auth.service.AuthService;
import com.nikhil.authservice.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    /*
    *   Controller method to handle register user API request
    * */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponse>> register(@Valid @RequestBody RegisterUserRequest registerUserRequest){
        // calling register method of AuthService
        RegisterUserResponse registeredUserResponse =  this.authService.register(registerUserRequest);

        // preparing structured response
        ApiResponse<RegisterUserResponse> apiResponse = new ApiResponse<RegisterUserResponse>(true,"User Registered.",registeredUserResponse, LocalDateTime.now());

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
}
