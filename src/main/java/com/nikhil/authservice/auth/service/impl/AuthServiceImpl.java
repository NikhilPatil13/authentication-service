package com.nikhil.authservice.auth.service.impl;

import com.nikhil.authservice.auth.dto.request.LoginRequest;
import com.nikhil.authservice.auth.dto.request.RegisterUserRequest;
import com.nikhil.authservice.auth.dto.response.LoginResponse;
import com.nikhil.authservice.auth.dto.response.RegisterUserResponse;
import com.nikhil.authservice.auth.service.AuthService;
import com.nikhil.authservice.exception.custom.EmailAlreadyExistsException;
import com.nikhil.authservice.exception.custom.EmailNotFoundException;
import com.nikhil.authservice.exception.custom.InvalidCredentialsException;
import com.nikhil.authservice.user.entity.User;
import com.nikhil.authservice.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

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

        // setting password. encoding password
        userToRegister.setPasswordHash(
                this.passwordEncoder.encode(
                    request.getPassword()
                )
        );

        // now save userToRegister by calling save method of UserRepository
        User registeredUser = this.userRepository.save(userToRegister);

        // convert registeredUser to RegisterResponseUser (DTO) to returning response
        return this.modelMapper.map(registeredUser , RegisterUserResponse.class);

    }


    /*
     *   method name : login
     *   inputs : LoginRequest (DTO)
     *   returns : LoginResponse (DTO)
     *   working : Used to login user by verifying email and password
     * */
    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        // checking user with provided email is present or not
        User foundUser = this.userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(EmailNotFoundException::new);

        // now we will verify the password
        boolean isPasswordMatched = this.passwordEncoder.matches(loginRequest.getPassword(), foundUser.getPasswordHash());

        if(!isPasswordMatched){
            // password not matched
            throw new InvalidCredentialsException();
        }

        // password matched
        // map foundUser with LoginResponse (DTO) and return
        return this.modelMapper.map(foundUser , LoginResponse.class);
    }
}
