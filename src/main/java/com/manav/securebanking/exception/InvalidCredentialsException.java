package com.manav.securebanking.exception;

public class InvalidCredentialsException extends RuntimeException{

    public InvalidCredentialsException(String message){
        super(message);
    }

}
