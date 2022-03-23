package com.company.repozitory;

import com.company.Exception.GameExceptions;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.Player;
import com.company.entity.matchEntity.PlayerType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
                preparedStatement.setString(3, p.getPlayerType().toString());
                preparedStatement.addBatch();
            }

            preparedStatement.executeBatch();
        } catch (Exception e){
            throw new GameExceptions("Error while inserting Team Players with team id: " + teamId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    public Player getPlayer (int playerId)  {
        Player player = null;
        try {
            String query = "SELECT * FROM PlayersTable WHERE PlayerId = " + playerId;
            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String pName = rs.getString(3);
            PlayerType playerType = PlayerType.valueOf(rs.getString(4));
            player = new Player(pName, playerType);
        } catch (Exception e){
            throw new GameExceptions("Error while getting  Player with player id: " + playerId, HttpStatus.NOT_FOUND);
        }

        return player;
    }





}


