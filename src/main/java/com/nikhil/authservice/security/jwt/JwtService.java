package com.nikhil.authservice.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtService {

    private final JwtProperties jwtProperties;

    public JwtService(JwtProperties jwtProperties){
        this.jwtProperties = jwtProperties;
    }

    // method to generate token
    public String generateToken(String email){
        System.out.println("in generate token");
        // taking expiration date
        Date now = new Date();

        Date expiryDate = new Date(
                now.getTime() + this.jwtProperties.getExpiration()
        );

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(getSigningKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    // method to generate secret key
    private SecretKey getSigningKey(){
        System.out.println("in getSigninKey()");
        byte[] keyBytes = this.jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8);

        return Keys.hmacShaKeyFor(keyBytes);
    }

    // method to extract username
    public String extractUsername(String token){
        System.out.println("in extractUsername()");
        return extractAllClaims(token).getSubject();
    }

    // extracts all claims
    private Claims extractAllClaims(String token){
        System.out.println("in extractAllClaims()");
        return Jwts.parser()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // method to check is token valid or not
    public  boolean isTokenValid(String token, String email){
        System.out.println("in isTokenValid()");
        String username = extractUsername(token);

        return username.equals(email) && !isTokenExpired(token);
    }

    // method to check expiration of token
    private boolean isTokenExpired(String token){
        System.out.println("in isTokenExpired()");
        Date expiration = extractAllClaims(token).getExpiration();

        return expiration.before(new Date());
    }
}
