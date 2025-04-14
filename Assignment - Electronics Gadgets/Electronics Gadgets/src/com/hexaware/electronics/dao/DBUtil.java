package com.hexaware.electronics.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil { // Factory class for electronics project

    public static Connection getDBConnection() throws SQLException {

        FileReader fr = null;
        Properties prop = new Properties();

        try {
            fr = new FileReader("src/Database.properties");
            prop.load(fr);
        } catch (FileNotFoundException e) {
            System.out.println("Database.properties file not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error loading database configuration.");
            e.printStackTrace();
        }

        String url = "jdbc:mysql://localhost:3306/TechShop";
        String username = "root";
        String password = "sandy2003";

        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(url, username, password);
    }
} 
