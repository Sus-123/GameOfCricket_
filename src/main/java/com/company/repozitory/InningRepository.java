package com.company.repozitory;

import com.company.Exception.ErrorDetails;
import com.company.constants.Constants;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.Inning;
import com.company.entity.matchEntity.OverDetails;
import com.company.entity.matchEntity.Strike;
import com.company.entity.matchEntity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

@Repository
public class InningRepository {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    OverDetailsRepository overDetailsRepository;

    //InningTable(InningId, BattingTeamId, BowlingTeamId, Overs);
    public int insertInning(int BattingTeamId, int BowlingTeamId, int Overs) {
        int inningId = 0;
        try {
            Connection connection = DbConnector.getConnection();
            connection.setAutoCommit(false);

            String query = "INSERT INTO InningTable (BattingTeamId, BowlingTeamId, Overs) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, BattingTeamId);
            preparedStatement.setInt(2, BowlingTeamId);
            preparedStatement.setInt(3, Overs);
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                inningId = rs.getInt(1);
            }
            connection.commit();
        } catch (Exception e){
            throw new IllegalStateException("Error while Inserting Inning In Db with Batting Team Id: " + BattingTeamId  +" Bowling Team Id : " + BowlingTeamId);
        }

        return inningId;
    }



    public Inning getInning(int id) {

        Inning inning = null;
        try {
            String query = " select * FROM InningTable where InningId = " + id;
            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();

            Team battingTeam = teamRepository.getTeam(rs.getInt(2));
            Team bowlingTeam = teamRepository.getTeam(rs.getInt(3));

            inning = new Inning(battingTeam, bowlingTeam, true, Constants.random, rs.getInt(4), new Strike());

            ArrayList<OverDetails> overDetails = overDetailsRepository.getOvers(id);
            inning.setOverDetailsArr(overDetails);
        } catch (Exception e){
            throw new IllegalStateException( "Error while getting Inning with Id : " + id);
        }

        return inning;

    }

    public  ArrayList<Team> getTeams (int inningId) {

        ArrayList<Team> teams = new ArrayList<>();
        try {
            String query = " select * FROM InningTable where InningId = " + inningId;

            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {
                teams.add(teamRepository.getTeam(rs.getInt(2)));
                teams.add(teamRepository.getTeam(rs.getInt(3)));
            }
        }  catch (Exception e){
            throw new IllegalStateException("Error while fetching teams with inningId: " + inningId);
        }

        if(teams.isEmpty()) {
            throw new IllegalStateException("With the inning Id : " + inningId + " teams does not exist!");
        }

        return teams;
    }


}







