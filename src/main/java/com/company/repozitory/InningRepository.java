package com.company.repozitory;

import com.company.database.DbConnector;
import com.company.entity.matchEntity.Inning;
import com.company.entity.matchEntity.OverDetails;
import com.company.entity.matchEntity.Strike;
import com.company.entity.matchEntity.Team;
import constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class InningRepository {

    @Autowired
    TeamRepository teamRepository;
    @Autowired
    OverDetailsRepository overDetailsRepository;

    //InningTable(InningId, BattingTeamId, BowlingTeamId, Overs);
    public int insertInning(int BattingTeamId, int BowlingTeamId, int Overs) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO InningTable (BattingTeamId, BowlingTeamId, Overs) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1, BattingTeamId);
        preparedStatement.setInt(2, BowlingTeamId);
        preparedStatement.setInt(3, Overs);

        int inningId = 0;
        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                inningId = rs.getInt(1);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

        return inningId;
    }



    public Inning createInning(int id) throws SQLException, ClassNotFoundException {

        String query = " select * FROM InningTable where InningId = " + id;
        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();

        Team battingTeam = teamRepository.createTeam(rs.getInt(2));
        Team bowlingTeam = teamRepository.createTeam(rs.getInt(3));

        Inning inning = new Inning(battingTeam, bowlingTeam, true, Constants.random, rs.getInt(4), new Strike());

        ArrayList<OverDetails> overDetails = overDetailsRepository.createOvers(id);

        inning.setOverDetailsArr(overDetails);

        return inning;

    }


}







