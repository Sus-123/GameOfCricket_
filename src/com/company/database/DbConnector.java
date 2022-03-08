package com.company.database;
import com.company.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {



    private static Connection connection = null;
    private static void initializeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(Constants.url, Constants.user, Constants.pass);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null || connection.isClosed()) {
            initializeConnection();
        }
        return connection;
    }

}
