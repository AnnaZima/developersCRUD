package com.anya.crudapp.utils;

import java.sql.*;

public class JdbcUtils {
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost/demobase";
    public static final String USER = "root";
    public static final String PASSWORD = "241663";

    public static PreparedStatement preparedStatement(String SQL) {
        try {
            return connectionToDB().prepareStatement(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static PreparedStatement preparedStatementWithKeys(String SQL) {
        try {
            return connectionToDB().prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Connection connectionToDB() {
        Connection connection = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Не удалось загрузить класс драйвера");
        }
        return connection;
    }
}
