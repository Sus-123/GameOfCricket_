package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class PlayersRepository {

    @Autowired
    TeamRepository teamRepository;

    //Table- PlayersTable (PlayerId, TeamId, PlayerName, PlayerType)
    public void insertTeamPlayers(int teamId, ArrayList<Player> players) throws SQLException, ClassNotFoundException {
        String query =  "INSERT INTO PlayersTable (`TeamId`, `PlayerName`, `PlayerType`) VALUES (?, ?, ?)";
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


    public  int getPlayerId (String player , String team) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        int team_Id = teamRepository.getTeamIdFromTeamName(team);
        String playerName = "'" + player + "'";
        String teamId = "'" + team_Id + "'";
        String query = "SELECT * FROM PlayersTable where TeamId = " + teamId + " and PlayerName = " + playerName;

        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        int id =-1;
        if(rs.next())
            id = rs.getInt(1);

        return id;

    }



    public  Player createPlayer (int playerId) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM PlayersTable WHERE PlayerId = " + playerId;
        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        Player p = new Player(rs.getString(3), rs.getString(4));
        return p;

    }



}


