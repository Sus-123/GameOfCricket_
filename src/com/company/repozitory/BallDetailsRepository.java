package com.company.repozitory;
import com.company.database.DbConnector;
import com.company.entity.BallDetails;
import com.company.entity.BallType;
import com.company.entity.Player;
import java.sql.*;
import java.util.ArrayList;

public class BallDetailsRepository {


    //BallDetailsTable (BallDetailsId, OverId, InningId, Score, StrikerId, BallType )
    public static void insertBallDetails (BallDetails ballDetails, int overId, String teamName, int inningId) throws SQLException, ClassNotFoundException {

        int scoreOnTheBall = ballDetails.getScoreOnBall();
        String strikerOnTheBall = ballDetails.getStrikerOnBall().getPlayerName();
        int strikerId  = PlayersRepository.getPlayerId(strikerOnTheBall, teamName);

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



    public static ArrayList<BallDetails> createBallDetails (int overId) throws SQLException, ClassNotFoundException {

        String query = "SELECT * FROM BallDetailsTable WHERE OverId = " + overId;
        Connection connection = DbConnector.getConnection();
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);

        ArrayList<BallDetails> ballDetails = new ArrayList<BallDetails>();

        while (rs.next()) {

            int strikerId = rs.getInt(5);
            Player striker = PlayersRepository.createPlayer(strikerId);

            BallDetails ball = new BallDetails();
            ball.setScoreOnBall(rs.getInt(4));
           // System.out.println(rs.getInt(4));
            ball.setBallType(BallType.valueOf(rs.getString(6)));
            ball.setStrikerOnBall(striker);
            ballDetails.add(ball);

        }

        return ballDetails;

    }


}


