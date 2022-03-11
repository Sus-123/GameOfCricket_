package com.company.repozitory;
import com.company.Exception.ErrorDetails;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.Team;
import com.company.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Date;


@Repository
public class TeamRepository {

    @Autowired
    PlayersRepository playersRepository;


    //Table- TeamTable (TeamId, TeamName)
    public int insertTeam(Team team)  {

         int teamId = 0;
         if(team == null) {
             throw new IllegalStateException("Null Team can not be inserted!");
         }
         String teamName = team.getName();
         if(checkIfTeamExist(teamName)) {
             return getTeamIdFromTeamName(teamName);
         }

         try {
            Connection connection = DbConnector.getConnection();
            connection.setAutoCommit(false);

            String query =  "INSERT INTO TeamTable (`TeamName`) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,teamName);

            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                teamId = rs.getInt(1);
            }

            playersRepository.insertTeamPlayers(teamId, team.getPlayers());
            connection.commit();

        } catch (Exception e ) {
             throw new ErrorDetails(new Date(), "Error while inserting Team with team name: " + teamName, e.getMessage());
         }

        return teamId;
    }

    public  Team createTeam (int teamId) {

        Team team = null;
        try {
            String query = " select * FROM TeamTable where TeamId = " + teamId;
            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            String teamName = "";
            if (rs.next()) {
                teamName = rs.getString(2);
            }
            team = new Team(teamName);
        } catch (Exception e) {
            throw new ErrorDetails(new Date(), "Error while getting Team with team id: " + teamId, e.getMessage());
        }
        return team;
    }


    public int getTeamIdFromTeamName (String team) {
        int teamId = 0;
        try {
            Connection connection = DbConnector.getConnection();
            String teamName = "'" + team + "'";
            String query = " select * FROM TeamTable where TeamName = " + teamName;

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                teamId = rs.getInt(1);
            }
        } catch (Exception e){
            throw new IllegalStateException("Error while getting Team Id from  db : " + e.getMessage());
        }

        return teamId;
    }


    public int getPlayerId (String player , String team)  {
        int id = 0 ;
        try {
            Connection connection = DbConnector.getConnection();
            int team_Id = getTeamIdFromTeamName(team);
            String playerName = "'" + player + "'";
            String teamId = "'" + team_Id + "'";
            String query = "SELECT * FROM PlayersTable where TeamId = " + teamId + " and PlayerName = " + playerName;

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next())
                id = rs.getInt(1);

        } catch (Exception e) {
            throw new ErrorDetails(new Date(), " Error wile getting Player: " + player + "from team "+ team, e.getMessage());
        }

        return id;

    }

    public boolean checkIfTeamExist (String team)  {
        boolean exist = false;
        try {
            Connection connection = DbConnector.getConnection();
            String teamName = "'" + team + "'";
            String query = " select * FROM TeamTable where TeamName = " + teamName;

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                exist = true;
            } else exist = false;
        } catch (Exception e){
            throw new IllegalStateException("Error while checking team existance in db : " + e.getMessage());
        }

        return exist;

    }

    public boolean checkIfPlayerExistInTeam(String teamName, String playerName) {
        boolean exist = false;
        int playerId = getPlayerId(playerName, teamName);
        try {
            String query = "SELECT * FROM PlayersTable WHERE PlayerId = " + playerId;
            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if( rs.next()) {
                exist = true;
            } else {
                exist = false;
            }
        } catch (Exception e){
            throw new ErrorDetails(new Date(), "Error while getting  Player with player id: " + playerId, e.getMessage());
        }

        return exist;
    }




}
