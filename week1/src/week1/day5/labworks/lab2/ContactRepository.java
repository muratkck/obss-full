package com.day5.lab2;

import java.util.List;

public interface ContactRepository {

    void createContact(Contact contact);
    List<Contact> listContacts();
    Contact getContactById(int searchId);
    void deleteContactById(int id);

}
