package com.example.contactapp.exceptions;

public class DatabaseConnectionException extends RuntimeException{
    public DatabaseConnectionException(){

    }
    public DatabaseConnectionException(String message){
        super(message);
    }
}
