package com.company.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private static  String user = "root";
    private static String pass = "Susu_1234"; 

    private static Connection connection = null;
    private static void initializeConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost/CrecketMatch","root",pass);
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if(connection == null || connection.isClosed()) {
            initializeConnection();
        }
        return connection;
    }

}
