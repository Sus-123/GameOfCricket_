package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.Player;
import com.company.entity.PlayerType;

import java.sql.*;
import java.util.ArrayList;

public class PlayersRepository {

    //Table- PlayersTable (PlayerId, TeamId, PlayerName, PlayerType)
    public static void insertTeamPlayers(int teamId, ArrayList<Player> players) throws SQLException, ClassNotFoundException {

        String query =  "INSERT INTO `CrecketMatch`.`PlayersTable` (`TeamId`, `PlayerName`, `PlayerType`) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = DbConnector.getConnection().prepareStatement(query);

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            preparedStatement.setInt(1,teamId);
            preparedStatement.setString(2,p.getPlayerName());
            preparedStatement.setString(3,p.getPlayerTypeInString());
            preparedStatement.addBatch();
        }

        preparedStatement.executeBatch();
    }


    public static int getPlayerId (String player , String team) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        int team_Id = TeamRepository.getTeamIdFromTeamName(team);
        String playerName = "'" + player + "'";
        String teamId = "'" + team_Id + "'";
        String query = "SELECT * FROM CrecketMatch.PlayersTable where TeamId = " + teamId + " and PlayerName = " + playerName;

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        int id =-1;
        if(rs.next())
            id = rs.getInt(1);

        return id;

    }



    public static Player createPlayer (int playerId) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM CrecketMatch.PlayersTable WHERE PlayerId = " + playerId;
        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        Player p = new Player(rs.getString(3), rs.getString(4));
        return p;

    }



}



//    public static ArrayList<Player> getAllPlayersFromTeamId(int teamId) throws SQLException, ClassNotFoundException {
//
//        Connection connection = DbConnector.getConnection();
//        Statement st = connection.createStatement();
//
//        ArrayList<Player> players = new ArrayList<Player>();
//        String query = "SELECT * FROM CrecketMatch.PlayersTable WHERE TeamId = " + teamId;
//        ResultSet rs = st.executeQuery(query);
//
//        while (rs.next()) {
//            Player p = new Player(rs.getString(3), rs.getString(4));
//            players.add(p);
//        }
//
//        return players;
//    }
//
//    public static ArrayList<Integer> getAllPlayersIdFromTeamId (int teamId) throws SQLException, ClassNotFoundException {
//
//        Connection connection = DbConnector.getConnection();
//        Statement st = connection.createStatement();
//
//        ArrayList<Integer> playersId = new ArrayList<>();
//        String query = "SELECT * FROM CrecketMatch.PlayersTable WHERE TeamId = " + teamId;
//        ResultSet rs = st.executeQuery(query);
//
//        while (rs.next()) {
//            playersId.add(rs.getInt(1));
//        }
//
//        return playersId;
//
//    }
