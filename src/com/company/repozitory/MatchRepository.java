package com.company.repozitory;
import com.company.database.DbConnector;
import java.sql.*;
import java.util.ArrayList;

public class MatchRepository {


    //Table- MatchTable(MatchId, Inning1Id, Inning2Id, MatchName)
    public static void insertMatch(int inning1Id, int inning2Id, String matchName) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query =  "INSERT INTO `CrecketMatch`.`MatchTable` (`Inning1Id`, `Inning2Id`, `MatchName`) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,inning1Id);
        preparedStatement.setInt(2,inning2Id);
        preparedStatement.setString(3,matchName);

        preparedStatement.executeUpdate();
        connection.commit();



    }

    public static int getMatchIdByName (String match) throws SQLException, ClassNotFoundException {

        String matchName = "'" + match + "'";
        String query = " select * FROM CrecketMatch.MatchTable where MatchName = " + matchName ;

        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        int id = -1;

        if(rs.next()) {
            id = rs.getInt(1);
        }

        return id;


    }

    public static ArrayList<Integer> getInningId (int matchId) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> InningIds = new ArrayList<>();

        String query = " select * FROM CrecketMatch.MatchTable where MatchId = " + matchId ;

        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        if(rs.next()) {
            InningIds.add(rs.getInt(2));
            InningIds.add(rs.getInt(3));
        }
        return InningIds;
    }

}
