package com.company.repozitory;

import com.company.database.DbConnector;
import constants.Constants;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class MatchRepository {


    //Table- MatchTable(MatchId, Inning1Id, Inning2Id, MatchName)
    public  int insertMatch(int inning1Id, int inning2Id, String matchName) throws SQLException, ClassNotFoundException {


        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO MatchTable (`Inning1Id`, `Inning2Id`, `MatchName`) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,inning1Id);
        preparedStatement.setInt(2,inning2Id);
        preparedStatement.setString(3,matchName);

        int matchId = 0;
        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                matchId = rs.getInt(1);
            }
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

        return matchId;

    }

    public int getMatchIdByName (String match) throws SQLException, ClassNotFoundException {

        String matchName = "'" + match + "'";
        String query = " select * FROM MatchTable where MatchName = " + matchName ;

        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        int id =0;

        try {
            if(rs.next()) {
                 id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }

        return id;

    }

    public  ArrayList<Integer> getInningId (int matchId) throws SQLException, ClassNotFoundException {
        ArrayList<Integer> InningIds = new ArrayList<>();

        String query = " select * FROM MatchTable where MatchId = " + matchId ;

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
