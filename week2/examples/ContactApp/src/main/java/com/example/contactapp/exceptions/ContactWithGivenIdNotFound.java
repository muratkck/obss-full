package com.example.contactapp.exceptions;

public class ContactWithGivenIdNotFound extends RuntimeException{
    public ContactWithGivenIdNotFound(int id) {
        super("Contact with id " + id + " not found");
    }
}
