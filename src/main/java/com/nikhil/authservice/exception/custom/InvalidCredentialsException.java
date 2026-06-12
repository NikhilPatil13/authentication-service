package com.nikhil.authservice.exception.custom;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(){
        super("Invalid email or password.");
    }
}
