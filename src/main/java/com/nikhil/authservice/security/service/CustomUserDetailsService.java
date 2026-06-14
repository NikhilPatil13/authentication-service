package com.nikhil.authservice.security.service;

import com.nikhil.authservice.user.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // spring security only understands UserDetailsService and not you repository
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.nikhil.authservice.user.entity.User user = this.userRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found with email : "+username)
        );

        return new User(
                user.getEmail(),
                user.getPasswordHash(),
                Collections.emptyList()
        );
    }
}
