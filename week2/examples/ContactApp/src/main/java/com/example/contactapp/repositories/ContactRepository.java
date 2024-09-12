package com.example.contactapp.repositories;

import com.example.contactapp.databaseItems.DbUtil;
import com.example.contactapp.exceptions.ContactWithGivenIdNotFound;
import com.example.contactapp.models.Contact;
import jakarta.validation.Valid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private Connection connection;

    public ContactRepository() {
        this.connection = DbUtil.createConnection();
    }

    public Contact getContactById(int id) {
        // null id control in service
        Contact contact = new Contact();
        String query = "SELECT * FROM contact_app WHERE id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()){
                throw new ContactWithGivenIdNotFound(id);
            }
            contact.setId(resultSet.getInt("id"));
            contact.setName(resultSet.getString("name"));
            contact.setPhoneNumber(resultSet.getString("phone_number"));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contact;
    }

    public void createContact(@Valid Contact contact) {
        //System.out.println("Creating contact " + contact);
        String query = "INSERT INTO contact_app (name, phone_number) VALUES (?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2,contact.getPhoneNumber());

            System.out.println("Contact is adding to the DB");
            preparedStatement.executeUpdate();
            System.out.println("Contact is added to the DB");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Contact> searchContactsByName(String contactName) {
        List<Contact> contacts = new ArrayList<>();

        String query = "SELECT * FROM contact_app WHERE name LIKE ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            // Needs revision
            preparedStatement.setString(1,contactName+"%");
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                contacts.add(new Contact(rs.getInt("id"), rs.getString("name"),rs.getString("phone_number")));
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void updateContact(Contact contact) {
        String query = "UPDATE contact_app SET name = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getPhoneNumber());
            preparedStatement.setInt(3, contact.getId());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new ContactWithGivenIdNotFound(contact.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
