package com.nikhil.authservice.exception.custom;

public class InvalidRefreshTokenException extends RuntimeException{

    public InvalidRefreshTokenException(){
        super("Invalid refresh token");
    }
}
