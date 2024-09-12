package com.day5.lab2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactRepositoryImplementation implements ContactRepository {

    private Connection connection;

    public ContactRepositoryImplementation() throws SQLException, ClassNotFoundException {
        this.connection = DBUtil.getConnection();
    }

    @Override
    public void createContact(Contact contact) {
        String query = "insert into contact (email, username, phone_number) values (?,?,?)";
        try {
            try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, contact.getEmail());
                preparedStatement.setString(2, contact.getName());
                preparedStatement.setString(3, contact.getPhone());

                preparedStatement.executeUpdate();
            };
        } catch (SQLException e) {
            System.out.println("Error while creating contact: " + e.getMessage());
        }
    }

    @Override
    public List<Contact> listContacts() {

        String query = "select * from contact";
        List<Contact> contacts = new ArrayList<>();

        try(Statement statement = connection.createStatement()){
            System.out.println("Query: " + query + " is running!");
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                String username = resultSet.getString("username");
                String phoneNumber = resultSet.getString("phone_number");
                String email = resultSet.getString("email");

                contacts.add(new Contact(username, phoneNumber, email));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contacts;
    }

    @Override
    public Contact getContactById(int searchId) {
        String query = "select * from contact where id=?";
        Contact contact = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, searchId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                contact = new Contact(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("phone_number"), resultSet.getString("email"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return contact;
    }

    @Override
    public void deleteContactById(int id) {
        String query = "delete from contact where id=?";

        try(PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
