package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.BallDetails;
import com.company.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BallDetailsRepository {


    //BallDetailsTable (Id, OverId, InningId, Score, StrikerId, BallType )
    public static void insertBallDetails (BallDetails ballDetails, int overId, String teamName, int inningId) throws SQLException, ClassNotFoundException {

        int scoreOnTheBall = ballDetails.getScoreOnBall();
        String strikerOnTheBall = ballDetails.getStrikerOnBall().getPlayerName();
        int strikerId  = DatabaseUtil.getPlayerIdFromDb(strikerOnTheBall, teamName);

        Connection connection = DbConnector.getConnection();
        connection.setAutoCommit(false);

        String query = "INSERT INTO `CrecketMatch`.`BallDetailsTable` (`OverId`, `InningId`, `Score`, `StrikerId`, `BallType` ) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, overId);
        preparedStatement.setInt(2, inningId);
        preparedStatement.setInt(3, scoreOnTheBall);
        preparedStatement.setInt(4, strikerId);
        preparedStatement.setString(5, ballDetails.getBallType().toString());

        preparedStatement.executeUpdate();
        connection.commit();

    }

}
