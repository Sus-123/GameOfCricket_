package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.BallDetails;
import com.company.entity.OverDetails;
import com.company.entity.Player;

import java.sql.*;
import java.util.ArrayList;

public class OverDetailsRepository {

    //OverDetailsTable (OverDetailsId, Inning1Id, BowlerId)
    public static int insertOverDetails (int inningId, String bowlerName, String teamName) throws SQLException, ClassNotFoundException {

        int bowlerId = PlayersRepository.getPlayerId(bowlerName, teamName);
        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO OverDetailsTable (`InningId`, `BowlerId`) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

        preparedStatement.setInt(1,inningId);
        preparedStatement.setInt(2,bowlerId);

        int overDetailsId  = 0;

        try {
            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                overDetailsId = rs.getInt(1);
            }
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        }

        return overDetailsId;

    }


    public static ArrayList<OverDetails> createOvers(int inningId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnector.getConnection();
        String query = "SELECT * FROM OverDetailsTable WHERE InningId = " + inningId;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        ArrayList<OverDetails> overs = new ArrayList<>();

        while (rs.next()) {

            int overId = rs.getInt(1);
            int bowlerId = rs.getInt(3);
            Player bowler = PlayersRepository.createPlayer(bowlerId);
            OverDetails overDetails = new OverDetails();
            ArrayList<BallDetails> ballDetails = BallDetailsRepository.createBallDetails (overId);
            overDetails.setBallDetails(ballDetails);
            overDetails.setBowler(bowler);
            overs.add(overDetails);
        }

        return overs;


    }


}
