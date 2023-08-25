package com.anya.crudapp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JdbcUtils {
   public static Connection getConnection() {
        Properties properties = new Properties();
        String jdbcDriver;
        String url;
        String user;
        String password;
        try {
            FileInputStream fis = new FileInputStream("src/main/resources/liquibase.properties");
            properties.load(fis);
            jdbcDriver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Class.forName(jdbcDriver);
            return DriverManager.getConnection(url,
                    user, password);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static PreparedStatement preparedStatement(String SQL) {
        try {
            return getConnection().prepareStatement(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement preparedStatementWithKeys(String SQL) {
        try {
            return getConnection().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
