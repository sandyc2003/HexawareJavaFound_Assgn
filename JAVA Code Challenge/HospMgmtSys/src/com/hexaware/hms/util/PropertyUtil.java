package com.hexaware.hms.util;

import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtil {

    public static String getPropertyString() {
        Properties properties = new Properties();
        String connectionString = "";

        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream("Database.properties")) {
            if (input == null) {
                System.err.println("Sorry, unable to find Database.properties");
                return "";
            }

            properties.load(input);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");

            connectionString = url + "?user=" + username + "&password=" + password;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return connectionString;
    }
}
