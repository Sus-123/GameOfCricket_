package com.company.database;
import java.sql.Connection;
import java.sql.DriverManager;

public class Database {

    private static  String user = "root";
    private static String pass = "Susu_1234";
    private static String url =  "jdbc:mysql://localhost/Cricket";


    public static Connection  makeConnection () {
         Connection connection = null;
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             connection = DriverManager.getConnection("jdbc:mysql://localhost/Cricket","root",pass);
             System.out.println("Connection Established");
        } catch (Exception e) {
            System.out.println(e);
        }
         return connection;
    }






}
