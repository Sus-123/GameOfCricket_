package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.BallDetails;
import com.company.entity.matchEntity.OverDetails;
import com.company.entity.matchEntity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;

@Repository
public class OverDetailsRepository {

    @Autowired
    PlayersRepository playersRepository;
    @Autowired
    BallDetailsRepository ballDetailsRepository;

    //OverDetailsTable (OverDetailsId, Inning1Id, BowlerId)
    public  int insertOverDetails (int inningId, String bowlerName, String teamName) throws SQLException, ClassNotFoundException {

        int bowlerId = playersRepository.getPlayerId(bowlerName, teamName);
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


    public  ArrayList<OverDetails> createOvers(int inningId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnector.getConnection();
        String query = "SELECT * FROM OverDetailsTable WHERE InningId = " + inningId;
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        ArrayList<OverDetails> overs = new ArrayList<>();

        while (rs.next()) {

            int overId = rs.getInt(1);
            int bowlerId = rs.getInt(3);
            Player bowler = playersRepository.createPlayer(bowlerId);
            OverDetails overDetails = new OverDetails();
            ArrayList<BallDetails> ballDetails = ballDetailsRepository.createBallDetails (overId);
            overDetails.setBallDetails(ballDetails);
            overDetails.setBowler(bowler);
            overs.add(overDetails);
        }

        return overs;

    }


}
