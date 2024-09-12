package com.example.contactapp.exceptions;

public class DatabaseDriverNotFoundException extends RuntimeException{
    public DatabaseDriverNotFoundException(){

    }
    public DatabaseDriverNotFoundException(String message){
        super(message);
    }
}
