package com.nikhil.authservice.auth.service;

import com.nikhil.authservice.auth.entity.RefreshToken;
import com.nikhil.authservice.user.entity.User;

public interface RefreshTokenService {
    // abstract method to create refresh token
    RefreshToken createRefreshToken(User user);

    // abstract method to validate refresh tokens
    RefreshToken validateRefreshToken(String token);

    // abstract method to revoke refresh token
    void revokeRefreshToken(String token);
}
