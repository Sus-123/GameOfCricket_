package com.company.repozitory;

import com.company.Exception.ErrorDetails;
import com.company.database.DbConnector;
import com.company.entity.matchEntity.BallDetails;
import com.company.entity.matchEntity.OverDetails;
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
public class OverDetailsRepository {

    @Autowired
    PlayersRepository playersRepository;
    @Autowired
    TeamRepository teamRepository;

    @Autowired
    BallDetailsRepository ballDetailsRepository;

    //OverDetailsTable (OverDetailsId, Inning1Id, BowlerId)
    public  int insertOverDetails (int inningId, String bowlerName, String teamName) {
        int overDetailsId = 0;

        try {
            int bowlerId = teamRepository.getPlayerId(bowlerName, teamName);
            Connection connection = DbConnector.getConnection();
            connection.setAutoCommit(false);

            String query = "INSERT INTO OverDetailsTable (`InningId`, `BowlerId`) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, inningId);
            preparedStatement.setInt(2, bowlerId);

            preparedStatement.execute();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                overDetailsId = rs.getInt(1);
            }
            connection.commit();
        } catch (Exception e){
            throw new IllegalStateException("Error while inserting overDetails with inning id: " + inningId);
        }

        return overDetailsId;

    }


    public  ArrayList<OverDetails> getOvers(int inningId)  {
        ArrayList<OverDetails> overs = new ArrayList<>();

        try {
            Connection connection = DbConnector.getConnection();
            String query = "SELECT * FROM OverDetailsTable WHERE InningId = " + inningId;
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                int overId = rs.getInt(1);
                int bowlerId = rs.getInt(3);
                Player bowler = playersRepository.getPlayer(bowlerId);
                OverDetails overDetails = new OverDetails();
                ArrayList<BallDetails> ballDetails = ballDetailsRepository.getBallDetails(overId);
                overDetails.setBallDetails(ballDetails);
                overDetails.setBowler(bowler);
                overs.add(overDetails);
            }
        } catch (Exception e){
            throw new IllegalStateException("Error while getting overDetails with inning id: " + inningId);
        }

        if(overs.isEmpty()) {
            throw new IllegalStateException("With Inning Id : " + inningId + " over details does not exist!");
        }

        return overs;

    }


}
