package com.company.repozitory;

import com.company.Exception.ErrorDetails;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.BallDetails;
import com.company.entity.matchEntity.BallType;
import com.company.entity.matchEntity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;


@Repository
public class BallDetailsRepository {

    @Autowired
    PlayersRepository playersRepository;

    @Autowired
    TeamRepository teamRepository;


    //BallDetailsTable (BallDetailsId, OverId, InningId, Score, StrikerId, BallType )
    public void insertBallDetails (BallDetails ballDetails, int overId, String teamName, int inningId)  {

        try {
            int scoreOnTheBall = ballDetails.getScoreOnBall();
            String strikerOnTheBall = ballDetails.getStrikerOnBall().getPlayerName();
            int strikerId = teamRepository.getPlayerId(strikerOnTheBall, teamName);

            Connection connection = DbConnector.getConnection();
            connection.setAutoCommit(false);

            String query = "INSERT INTO BallDetailsTable (`OverId`, `InningId`, `Score`, `StrikerId`, `BallType` ) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, overId);
            preparedStatement.setInt(2, inningId);
            preparedStatement.setInt(3, scoreOnTheBall);
            preparedStatement.setInt(4, strikerId);
            preparedStatement.setString(5, ballDetails.getBallType().toString());

            preparedStatement.executeUpdate();
            connection.commit();
        } catch (Exception e){
            throw new IllegalStateException("Error while Inserting Ball Details with OverId  : " + overId);
        }

    }



    public ArrayList<BallDetails> getBallDetails (int overId)  {

        ArrayList<BallDetails> ballDetails = new ArrayList<BallDetails>();
        try {
            String query = "SELECT * FROM BallDetailsTable WHERE OverId = " + overId;
            Connection connection = DbConnector.getConnection();
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                int strikerId = rs.getInt(5);
                Player striker = playersRepository.getPlayer(strikerId);

                BallDetails ball = new BallDetails();
                ball.setScoreOnBall(rs.getInt(4));
                ball.setBallType(BallType.valueOf(rs.getString(6)));
                ball.setStrikerOnBall(striker);
                ballDetails.add(ball);

            }
        } catch (Exception e){
            throw new IllegalStateException("Error while getting Ball Details with OverId : " + overId);
        }

        if(ballDetails.isEmpty()) {
            throw new IllegalStateException("With over Id : " + overId + " Ball details does not exist!");
        }

        return ballDetails;

    }


}


