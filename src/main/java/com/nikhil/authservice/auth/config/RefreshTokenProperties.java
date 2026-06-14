package com.nikhil.authservice.auth.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "refresh-token")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenProperties {

    private Long expiration;
}
