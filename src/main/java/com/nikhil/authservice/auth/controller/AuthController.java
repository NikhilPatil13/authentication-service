package com.nikhil.authservice.auth.controller;

import com.nikhil.authservice.auth.dto.request.LoginRequest;
import com.nikhil.authservice.auth.dto.request.LogoutRequest;
import com.nikhil.authservice.auth.dto.request.RefreshTokenRequest;
import com.nikhil.authservice.auth.dto.request.RegisterUserRequest;
import com.nikhil.authservice.auth.dto.response.LoginResponse;
import com.nikhil.authservice.auth.dto.response.RefreshTokenResponse;
import com.nikhil.authservice.auth.dto.response.RegisterUserResponse;
import com.nikhil.authservice.auth.service.AuthService;
import com.nikhil.authservice.common.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    /*
    *   Controller method to handle register user api request
    * */
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegisterUserResponse>> register(@Valid @RequestBody RegisterUserRequest registerUserRequest){
        // calling register method of AuthService
        RegisterUserResponse registeredUserResponse =  this.authService.register(registerUserRequest);

        // preparing structured response
        ApiResponse<RegisterUserResponse> apiResponse = new ApiResponse<RegisterUserResponse>(true,"User Registered.",registeredUserResponse, LocalDateTime.now());

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    /*
     *   Controller method to handle login user api request
     * */
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>>login(@Valid @RequestBody LoginRequest loginRequest){
        // calling login method of AuthService
        LoginResponse loginResponse = this.authService.login(loginRequest);

        // preparing response
        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(true,"Login Successful",loginResponse,LocalDateTime.now());

        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }

    /*
     *   Controller method to handle profile user api request
     * */
    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<String>> profile(Authentication authentication){
        return ResponseEntity.ok(new ApiResponse<>(
                true,
                "Profile fetched sucessfully.",
                authentication.getName(),
                LocalDateTime.now()
        ));
    }


    /*
     *   Controller method to handle refresh token api request
     * */
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>>refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        RefreshTokenResponse refreshTokenResponse = this.authService.refreshToken(refreshTokenRequest);

        // preparing response
        ApiResponse<RefreshTokenResponse> apiResponse = new ApiResponse<>(true,"Token refreshed.",refreshTokenResponse,LocalDateTime.now());

        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }


    /*
     *   Controller method to handle logout
     * */
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<RefreshTokenResponse>>logout(@RequestBody LogoutRequest logoutRequest){
        this.authService.logout(logoutRequest);

        // preparing response
        ApiResponse<RefreshTokenResponse> apiResponse = new ApiResponse<>(true,"Logout sucessfully.",null,LocalDateTime.now());

        return new ResponseEntity<>(apiResponse , HttpStatus.OK);
    }

}
