package com.company.repozitory;
import com.company.Constants;
import com.company.database.DbConnector;
import com.company.entity.Inning;
import com.company.entity.OverDetails;
import com.company.entity.Strike;
import com.company.entity.Team;

import java.sql.*;
import java.util.ArrayList;

public class InningRepository {

    //InningTable(InningId, BattingTeamId, BowlingTeamId, Overs);
    public static int insertInning(int BattingTeamId, int BowlingTeamId, int Overs) throws SQLException, ClassNotFoundException {

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        // String query = "insert into Inning(Match_Id, BattingTeam_Id, BowlingTeam_Id) values(?,?,?)";
        String query = "INSERT INTO `CrecketMatch`.`InningTable` (BattingTeamId, BowlingTeamId, Overs) VALUES (?, ?, ?)";

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

    public static Inning createInning(int id) throws SQLException, ClassNotFoundException {

        String query = " select * FROM CrecketMatch.InningTable where InningId = " + id;
        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();

        Team battingTeam = TeamRepository.createTeam(rs.getInt(2));
        Team bowlingTeam = TeamRepository.createTeam(rs.getInt(3));

        Inning inning = new Inning(battingTeam, bowlingTeam, true, Constants.random, Constants.random, new Strike());

        ArrayList<OverDetails> overDetails = OverDetailsRepository.createOvers(id);

        inning.setOverDetailsArr(overDetails);

        return inning;

    }

}







