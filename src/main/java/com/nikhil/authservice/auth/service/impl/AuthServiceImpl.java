package com.nikhil.authservice.auth.service.impl;

import com.nikhil.authservice.auth.dto.request.RegisterUserRequest;
import com.nikhil.authservice.auth.dto.response.RegisterUserResponse;
import com.nikhil.authservice.auth.service.AuthService;
import com.nikhil.authservice.exception.custom.EmailAlreadyExistsException;
import com.nikhil.authservice.user.entity.User;
import com.nikhil.authservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    /*
    *   method name : register
    *   inputs : RegisterUserRequest (DTO)
    *   returns : RegisterUserResponse (DTO)
    *   working : Used to register new user in the database by checking email.
    * */
    @Override
    public RegisterUserResponse register(RegisterUserRequest request) {
        // checking user with provided mail id is already present or not
        if(userRepository.existsByEmail(request.getEmail())){
            // user with provided email id is found
            throw new EmailAlreadyExistsException(
                    request.getEmail()
            );
        }

        // user not found - do register
        // save
        // mapping this request with User
        User userToRegister = this.modelMapper.map(request, User.class);

        // setting values of isActive , createdAt and updatedAt
        userToRegister.setIsActive(true);

        LocalDateTime now = LocalDateTime.now();
        userToRegister.setCreatedAt(now);
        userToRegister.setUpdatedAt(now);

        // setting password. later we will save password by using BCryptPasswordEncoder
        userToRegister.setPasswordHash(request.getPassword());

        // now save userToRegister by calling save method of UserRepository
        User registeredUser = this.userRepository.save(userToRegister);

        // convert registeredUser to RegisterResponseUser (DTO) to returning response
        return this.modelMapper.map(registeredUser , RegisterUserResponse.class);

    }
}
