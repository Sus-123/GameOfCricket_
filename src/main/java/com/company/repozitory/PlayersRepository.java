package com.company.repozitory;
import com.company.Exception.ErrorDetails;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class PlayersRepository {


    //Table- PlayersTable (PlayerId, TeamId, PlayerName, PlayerType)
    public void insertTeamPlayers(int teamId, ArrayList<Player> players)  {

        if(players.isEmpty()) {
            throw new IllegalStateException("With team Id : " + teamId + " players does not exist!");
        }

        try {
            String query = "INSERT INTO PlayersTable (`TeamId`, `PlayerName`, `PlayerType`) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = DbConnector.getConnection().prepareStatement(query);

            for (int i = 0; i < players.size(); i++) {
                Player p = players.get(i);
                preparedStatement.setInt(1, teamId);
                preparedStatement.setString(2, p.getPlayerName());
                preparedStatement.setString(3, p.getPlayerTypeInString());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (Exception e){
            throw new ErrorDetails(new Date(), "Error while inserting Team Players with team id: " + teamId, e.getMessage());
        }
    }




    public Player getPlayer (int playerId)  {
        Player p = null;
        try {
            String query = "SELECT * FROM PlayersTable WHERE PlayerId = " + playerId;
            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            p = new Player(rs.getString(3), rs.getString(4));
        } catch (Exception e){
            throw new ErrorDetails(new Date(), "Error while getting  Player with player id: " + playerId, e.getMessage());
        }

        return p;

    }





}


