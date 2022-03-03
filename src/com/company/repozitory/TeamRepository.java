package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.Team;

import java.sql.*;

public class TeamRepository {


    //Table- TeamTable (TeamId, TeamName)
    public static int insertTeam(Team team) throws SQLException, ClassNotFoundException {

        String teamName = team.getName();

        if(getTeamIdFromTeamName(teamName) != -1) {
            return getTeamIdFromTeamName(teamName);
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

    public static Team createTeam (int teamId) throws SQLException, ClassNotFoundException {

        String query = " select * FROM CrecketMatch.TeamTable where TeamId = " + teamId ;
        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        String teamName = "";
        if(rs.next()) {
            teamName = rs.getString(2);
        }

        Team team = new Team(teamName);
        return team;

    }


    public static int getTeamIdFromTeamName (String team) throws SQLException, ClassNotFoundException {

        String teamName = "'" + team + "'";
        String query = " select * FROM CrecketMatch.TeamTable where TeamName = " + teamName ;

        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        int id =-1;

        if(rs.next()) {
            id = rs.getInt(1);
        }

        return id;
    }


}
