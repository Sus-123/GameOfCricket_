package com.company.repozitory;

import com.company.Exception.GameExceptions;
import com.company.database.DbConnector;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@Repository
public class MatchRepository {


    //Table- MatchTable(MatchId, Inning1Id, Inning2Id, MatchName)
    public  int insertMatch(int inning1Id, int inning2Id, String matchName)  {
        int matchId = 0;
        try {
            Connection connection = DbConnector.getConnection();
            connection.setAutoCommit(false);

            String query = "INSERT INTO MatchTable (`Inning1Id`, `Inning2Id`, `MatchName`) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, inning1Id);
            preparedStatement.setInt(2, inning2Id);
            preparedStatement.setString(3, matchName);


            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                matchId = rs.getInt(1);
            }
        } catch (Exception e){
            throw new GameExceptions("Error while inserting Match : " + matchName, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return matchId;
    }

    public int getMatchIdByName (String match)  {
        int id =0;
        try {
            String matchName = "'" + match + "'";
            String query = " select * FROM MatchTable where MatchName = " + matchName;

            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (Exception e){
            throw new GameExceptions("Error while fetching MatchId of Match  : " + match, HttpStatus.NOT_FOUND);
        }

        return id;
    }



    public  ArrayList<Integer> getInningId (int matchId) {

        ArrayList<Integer> InningIds = new ArrayList<>();
        try {
            String query = " select * FROM MatchTable where MatchId = " + matchId;

            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                InningIds.add(rs.getInt(2));
                InningIds.add(rs.getInt(3));
            }
        }  catch (Exception e){
            throw new GameExceptions("Error while fetching InningIds with matchId: " + matchId, HttpStatus.NOT_FOUND);
        }

        if(InningIds.isEmpty()) {
            throw new GameExceptions("With the match Id : " + matchId + " Innings does not exist!",HttpStatus.NO_CONTENT );//The server has fulfilled the request but does not need to return an entity-body, and might want to return updated metainformation.
        }

        return InningIds;
    }


    public boolean checkIfMatchExist (String match)  {
        boolean exist = false;
        try {
            String matchName = "'" + match + "'";
            String query = " select * FROM MatchTable where MatchName = " + matchName;

            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                exist = true;
            }
        } catch (Exception e){
            throw new GameExceptions("Error while Checking match existence in Db with Match: " + match, HttpStatus.NOT_FOUND);
        }
        return exist;
    }

}
