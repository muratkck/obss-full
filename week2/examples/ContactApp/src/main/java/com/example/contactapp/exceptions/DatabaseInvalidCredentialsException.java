package com.example.contactapp.exceptions;

public class DatabaseInvalidCredentialsException extends RuntimeException{
    public DatabaseInvalidCredentialsException() {

    }
    public DatabaseInvalidCredentialsException(String message) {
        super(message);
    }
}
