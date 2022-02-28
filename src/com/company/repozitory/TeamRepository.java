package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.Team;
import com.company.util.DatabaseUtil;

import java.sql.*;

public class TeamRepository {


    //Table- TeamTable (Id, TeamName)
    public static int insertTeam(Team team) throws SQLException, ClassNotFoundException {

        String teamName = team.getName();

        if(DatabaseUtil.getTeamIdFromTeamName(teamName) != -1) {
            return DatabaseUtil.getTeamIdFromTeamName(teamName);
        }

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query =  "INSERT INTO `CrecketMatch`.`TeamTable` (`TeamName`) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1,teamName);

        int teamId = 0;
        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                teamId = rs.getInt(1);
            }

            PlayersRepository.insertTeamPlayers(teamId, team.getPlayers());
            connection.commit();

        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }
        return teamId;
    }

}
