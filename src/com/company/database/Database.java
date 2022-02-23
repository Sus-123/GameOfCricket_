package com.company.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private String user;
    private String pass;
    private String url ;
    private Connection con;

    public void  makeConnection () {
        this.user = "root";
        this.pass = "Susu_1234";
        this.url = "jdbc:mysql://localhost/Cricket";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.con = DriverManager.getConnection("jdbc:mysql://localhost/Cricket","root",pass);
        } catch (Exception e) {
            System.out.println(e);
        }
    }






}
