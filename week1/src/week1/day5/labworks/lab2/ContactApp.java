package com.day5.lab2;

import java.sql.*;
import java.util.Scanner;

public class ContactApp {

    public static void main(String[] args) {


        Contact contact1 = new Contact("murat1", "555 555 55 52", "murat1@gmail.com");
        Contact contact2 = new Contact("murat2", "555 555 55 53", "murat2@gmail.com");
        Contact contact3 = new Contact("murat3", "555 555 55 54", "murat3@gmail.com");
        Contact contact4 = new Contact("murat4", "555 555 55 55", "murat4@gmail.com");

        try {
            ContactRepository contactRepository = new ContactRepositoryImplementation();

//            contactRepository.createContact(contact1);
//            contactRepository.createContact(contact2);
//            contactRepository.createContact(contact3);
//            contactRepository.createContact(contact4);

            contactRepository.listContacts().forEach(System.out::println);
            contactRepository.deleteContactById(38);
            contactRepository.listContacts().forEach(System.out::println);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



}
