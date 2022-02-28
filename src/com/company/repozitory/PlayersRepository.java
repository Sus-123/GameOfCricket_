package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.Player;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlayersRepository {

    //Table- PlayersTable (Id, TeamId, PlayerName, PlayerType)
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

}
