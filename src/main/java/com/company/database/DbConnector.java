package com.company.database;
import com.company.dataProvider.ConfigFileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DbConnector {

    private static String user;
    private static String pass;
    private static String url;
    private static Connection connection = null;

    private static void initializeConnection() {
        try {
            Properties properties = ConfigFileReader.getProperties("src/main/resources/application.properties");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            pass = properties.getProperty("pass");
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
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
