package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.Inning;
import com.company.entity.matchEntity.Team;
import com.company.util.InningUtil;
import constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;


@Repository
public class TeamRepository {

    @Autowired
    PlayersRepository playersRepository;


    //Table- TeamTable (TeamId, TeamName)
    public int insertTeam(Team team)  {

         String teamName = team.getName();

         if( getTeamIdFromTeamName(teamName) != Constants.inValid) return getTeamIdFromTeamName(teamName);

         int teamId = 0;
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
             e.printStackTrace();
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
            e.printStackTrace();;
        }
        return team;
    }


    public int getTeamIdFromTeamName (String team) {

        try {
            Connection connection = DbConnector.getConnection();
            String teamName = "'" + team + "'";
            String query = " select * FROM TeamTable where TeamName = " + teamName;

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return Constants.inValid;
    }


}
