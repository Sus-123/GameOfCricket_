package com.company.database;
import com.company.entity.*;
import com.company.util.DatabaseUtil;
import com.company.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;


public class DbOperations {



    //Table- Team (Id, Team_Name)
    public static int insertTeamInDb(Team team) throws SQLException, ClassNotFoundException {
        String teamName = team.getName();

        if(DatabaseUtil.getTeamIdFromTeamName(teamName) != -1) {
            return DatabaseUtil.getTeamIdFromTeamName(teamName);
        }

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        // String query = "insert into Team(Team_Name) values(?)";
        String query =  "INSERT INTO `CrecketMatch`.`Team` (`Team_Name`) VALUES (?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,teamName);
        int teamId = 0;
        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                teamId = rs.getInt(1);
            }
            insertTeamPlayersInDb(teamId, team.getPlayers());
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return teamId;
    }


    //Table- Players (Id, Team_id, Player_Name, Player_Type)
    public static void insertTeamPlayersInDb(int teamId, ArrayList<Player> players) throws SQLException, ClassNotFoundException {

        String query =  "INSERT INTO `CrecketMatch`.`Players` (`Team_Id`, `Player_Name`, `Player_Type`) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = DbConnector.getConnection().prepareStatement(query);

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            preparedStatement.setInt(1,teamId);
            preparedStatement.setString(2,p.getPlayerName());
            preparedStatement.setString(3,p.getPlayerTypeInString());
            //preparedStatement.executeUpdate();
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();

    }


    //Table- Match(Id, BattingTeam_Id, BowlingTeam_Id, Overs)
    public static int insertMatchInDb(int team1Id, int team2Id, int noOfOvers) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        //String query = "insert into Match (Team1_Id, Team2_Id, Overs) VALUE (?,?,?)";
        String query =  "INSERT INTO `CrecketMatch`.`Match` (`BattingTeam_Id`, `BowlingTeam_Id`, `Overs`) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,team1Id);
        preparedStatement.setInt(2,team2Id);
        preparedStatement.setInt(3,noOfOvers);

        int matchId = 0;
        try {
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            while (rs.next()) {
                matchId = rs.getInt(1);
            }
            connection.commit();
        } catch (Exception e) {
            throw e;
        }
        return matchId;
    }



    //Table - Inning(Match_Id);
    public static int insertInningInDb (int matchId) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

       // String query = "insert into Inning(Match_Id, BattingTeam_Id, BowlingTeam_Id) values(?,?,?)";
        String query =  "INSERT INTO `CrecketMatch`.`Inning` (`Match_Id`) VALUES (?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,matchId);

        int inningId = 0;
        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                inningId = rs.getInt(1);
            }

            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return inningId;

    }



    //OverDetails (Id, Inning_Id, Bowler_Id)
    public static int insertOverInDb (int inningId, String bowlerName, String teamName) throws SQLException, ClassNotFoundException {

        int bowlerId = DatabaseUtil.getPlayerIdFromDb(bowlerName, teamName);
        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO `CrecketMatch`.`OverDetails` (`Inning_Id`, `Bowler_Id`) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,inningId);
        preparedStatement.setInt(2,bowlerId);

        int overDetailsId  = 0;

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                overDetailsId = rs.getInt(1);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

        return overDetailsId;

    }



    //Table - BallDetails (OverDetails_Id, Score, StrikerId, Wicket(boolean))
    public static void insertBallDetailsInDb (BallDetails ballDetails, int overDetailsId, String teamName) throws SQLException, ClassNotFoundException {

        int scoreOnTheBall = ballDetails.getScoreOnBall();
        String strikerOnTheBall = ballDetails.getStrikerOnBall().getPlayerName();
        BallType ballType = ballDetails.getBallType();

        int strikerId  = DatabaseUtil.getPlayerIdFromDb(strikerOnTheBall, teamName);

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO `CrecketMatch`.`BallDetails` (`OverDetails_Id`, `Score`, `Striker_Id`, `Wicket` ) VALUES (?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, overDetailsId);
        preparedStatement.setInt(2,scoreOnTheBall);
        preparedStatement.setInt(3,strikerId);
        if(ballType == BallType.WICKET)
            preparedStatement.setInt(4,1);
        else
            preparedStatement.setInt(4,0);

        preparedStatement.executeUpdate();

    }




    //    //Table-
//    public static void storeGameResult (Inning inning1, Inning inning2, int matchId) throws SQLException, ClassNotFoundException {
//        int scoreTeam1 = Util.getScoreOfInning(inning1);
//        int scoreTeam2 = Util.getScoreOfInning(inning2);
//        int oversPlayedTeam1 = inning1.getOverDetails().size();
//        int oversPlayedTeam2 = inning2.getOverDetails().size();
//
//
//        Connection connection = DbConnector.getConnection();
//        connection.setAutoCommit(false);
//
//        String query = "insert into MatchResult (Match_Id, Team1_Score,Team2_Score,Team1_Overs, Team2_Overs) values (?,?,?,?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//        preparedStatement.setInt(1,matchId);
//        preparedStatement.setInt(2,scoreTeam1);
//        preparedStatement.setInt(3,scoreTeam2);
//        preparedStatement.setInt(4,oversPlayedTeam1);
//        preparedStatement.setInt(5,oversPlayedTeam2);
//
//        preparedStatement.addBatch();
//        preparedStatement.executeBatch();
//
//    }

//    //PlayerWiseScore ( matchId, teamId, Player, score, ballsPlayed, wicketTaken )
//    public static void storePlayerWiseScore (Inning inning1, Inning inning2, int team1, int team2, int matchId) throws SQLException, ClassNotFoundException {
//
//        storePlayerResults(inning1, inning2,team1,matchId);
//        storePlayerResults(inning2,inning1,team2,matchId);
//
//    }

//    public  static void  storePlayerResults (Inning inning1, Inning inning2, int team, int matchId) throws SQLException, ClassNotFoundException {
//
//        Connection connection = DbConnector.getConnection();
//        connection.setAutoCommit(false);
//
//        String query = "insert into PlayerWiseResult (Match_Id, Team_Id, Player, Score, Balls_Played, Wicket_Taken) values (?,?,?,?,?,?)";
//        PreparedStatement preparedStatement = connection.prepareStatement(query);
//
//        for (int i = 0; i <= 10; i++) {
//            Player player = inning1.getBattingTeam().getPlayers().get(i);
//            String name = player.getPlayerName();
//            int score = Util.getPlayerWiseScore(player,inning1);
//            int ballsPlayed = Util.getTotalBallsPlayed(player, inning1);
//            int wicketTaken = Util.getWicketTakenByBowler(player,inning2);
//
//            preparedStatement.setInt(1,matchId);
//            preparedStatement.setInt(2,team);
//            preparedStatement.setString(3,name);
//            preparedStatement.setInt(4,score);
//            preparedStatement.setInt(5,ballsPlayed);
//            preparedStatement.setInt(6,wicketTaken);
//
//            preparedStatement.addBatch();
//        }
//
//        preparedStatement.executeBatch();
//
//    }




}









