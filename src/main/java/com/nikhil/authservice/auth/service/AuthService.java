package com.nikhil.authservice.auth.service;

import com.nikhil.authservice.auth.dto.request.RegisterUserRequest;
import com.nikhil.authservice.auth.dto.response.RegisterUserResponse;

public interface AuthService {
    /*
    *   abstract method to use register new user
    * */
    RegisterUserResponse register(RegisterUserRequest request);
}
