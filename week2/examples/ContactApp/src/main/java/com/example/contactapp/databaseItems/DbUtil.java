package com.example.contactapp.databaseItems;

import com.example.contactapp.exceptions.DatabaseDriverNotFoundException;
import com.example.contactapp.exceptions.DatabaseInvalidCredentialsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";

    public static Connection createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); // here is the ClassNotFoundException

            String url = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/jip";
            //System.out.println("Connecting: " + url);

            Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);

            //System.out.println("Connected successfully!");
            //System.out.println("********************");

            return connection;
        }
        catch (ClassNotFoundException e) {
            //e.printStackTrace();
            throw new DatabaseDriverNotFoundException("Database driver not found!");
        }
        catch (SQLException e) {
            throw new DatabaseInvalidCredentialsException("Database credentials invalid!");
        }
    }
}
