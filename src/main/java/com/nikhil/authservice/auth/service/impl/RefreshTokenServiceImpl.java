package com.nikhil.authservice.auth.service.impl;

import com.nikhil.authservice.auth.config.RefreshTokenProperties;
import com.nikhil.authservice.auth.entity.RefreshToken;
import com.nikhil.authservice.auth.repository.RefreshTokenRepository;
import com.nikhil.authservice.auth.service.RefreshTokenService;
import com.nikhil.authservice.exception.custom.InvalidRefreshTokenException;
import com.nikhil.authservice.user.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenProperties refreshTokenProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenProperties refreshTokenProperties, RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenProperties = refreshTokenProperties;
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(
                UUID.randomUUID().toString()
        );

        refreshToken.setCreatedAt(
                LocalDateTime.now()
        );

        refreshToken.setExpiryDate(
                LocalDateTime.now().plusSeconds(
                        refreshTokenProperties.getExpiration()/1000
                )
        );

        refreshToken.setRevoked(false);
        refreshToken.setUser(user);

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken validateRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(InvalidRefreshTokenException::new);


        if(Boolean.TRUE.equals(refreshToken.getRevoked())){
            throw new InvalidRefreshTokenException();
        }

        if(refreshToken.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new InvalidRefreshTokenException();
        }

        return refreshToken;
    }

    @Override
    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = validateRefreshToken(token);

        refreshToken.setRevoked(true);

        refreshTokenRepository.save(refreshToken);
    }
}
