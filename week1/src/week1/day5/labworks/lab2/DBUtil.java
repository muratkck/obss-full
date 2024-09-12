package com.day5.lab2;

import java.sql.*;

public class DBUtil {

    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "3306";
    private static final String DB_USER = "murat";
    private static final String DB_PASSWORD = "sanane64";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver"); // here is the ClassNotFoundException

        String url = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/jip";
        System.out.println("Connecting: " + url);

        Connection connection = DriverManager.getConnection(url, DB_USER, DB_PASSWORD);

        System.out.println("Connected successfully!");
        System.out.println("********************");

        return connection;
    }
}
