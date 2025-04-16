package com.hexaware.hms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    
    private static Connection connection;

    
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            
            String connectionString = PropertyUtil.getPropertyString();

            try {
                
                Class.forName("com.mysql.cj.jdbc.Driver");

                
                connection = DriverManager.getConnection(connectionString);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("MySQL JDBC Driver not found.");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Failed to establish connection to the database.");
            }
        }
        return connection;
    }
}
