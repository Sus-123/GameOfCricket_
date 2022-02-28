package com.company.util;

import com.company.database.DbConnector;

import java.sql.*;

public class DatabaseUtil {


   public static int getPlayerIdFromDb (String player , String team) throws SQLException, ClassNotFoundException {


       Connection connection = DbConnector.getConnection();

       int team_Id = getTeamIdFromTeamName(team);

       String playerName = "'" + player + "'";
       String teamId = "'" + team_Id + "'";

       String query = "SELECT FROM Players where Team_Id = " + teamId + " and Player_Name = " + playerName;

       Statement st = connection.createStatement();

       ResultSet rs = st.executeQuery(query);

       rs.next();

       int id = rs.getInt(1);

       return id;

   }

   public static int getTeamIdFromTeamName (String team) throws SQLException, ClassNotFoundException {

       String teamName = "'" + team + "'";
      // String query = "SELECT FROM Team where Team_Name = " + teamName ;
       String query = " select * FROM CrecketMatch.Team where Team_Name = " + teamName ;

       Connection connection = DbConnector.getConnection();

       Statement st = connection.createStatement();

       ResultSet rs = st.executeQuery(query);
       int id =-1;

       if(rs.next()) {
           id = rs.getInt(1);
          // System.out.println(id);
          // return  id;
       }

       return id;
   }


}
