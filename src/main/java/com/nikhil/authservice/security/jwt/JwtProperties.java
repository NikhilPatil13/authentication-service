package com.nikhil.authservice.security.jwt;

// reads JWT configuration from application.yml

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@ConfigurationProperties(prefix = "jwt") // takes values from application.yml
public class JwtProperties {
    private String secretKey;
    private Long expiration;
}
