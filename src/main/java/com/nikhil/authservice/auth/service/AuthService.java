package com.nikhil.authservice.auth.service;

import com.nikhil.authservice.auth.dto.request.LoginRequest;
import com.nikhil.authservice.auth.dto.request.RegisterUserRequest;
import com.nikhil.authservice.auth.dto.response.LoginResponse;
import com.nikhil.authservice.auth.dto.response.RegisterUserResponse;

public interface AuthService {
    /*
    *   abstract method to add register new user
    * */
    RegisterUserResponse register(RegisterUserRequest request);


    /*
    *   abstract method to login user
    * */
    LoginResponse login(LoginRequest loginRequest);

}
