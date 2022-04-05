package com.company.database;
import com.company.config.FileReaderConfiguration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DbConnector {
    
    private static Connection connection = null;

    private static void initializeConnection() {
        try {
            Properties properties = FileReaderConfiguration.getProperties("src/main/resources/application.properties");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(properties.getProperty("url"),  properties.getProperty("user"), properties.getProperty("pass"));
        } catch (Exception e){
            throw new IllegalStateException("Some Internal Error while connecting with database");
        }
    }

    public static Connection getConnection() {
        try{
            if(connection == null || connection.isClosed()) {
                initializeConnection();
            }
            return connection;
        } catch (Exception e){
            e.printStackTrace();
            throw new IllegalStateException("Some Internal Error while connecting with database");
        }
    }

}
