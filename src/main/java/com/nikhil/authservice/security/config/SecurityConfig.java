package com.nikhil.authservice.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/*
*   This class provides PasswordEncoder for converting plain password into hashed password
*   This class checks the allowed/not allowed endpoints
*
*   Needs -> spring-boot-starter-security
* */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // method to authorising requests
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(
                                "api/v1/auth/register",
                                "api/v1/auth/login"
                        )
                                .permitAll()
                                .anyRequest().authenticated()

                );

        return httpSecurity.build();
    }
}
