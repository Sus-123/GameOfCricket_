package com.company.service;
import com.company.Constants;
import com.company.entity.*;
import com.company.repozitory.BallDetailsRepository;
import com.company.repozitory.OverDetailsRepository;
import com.company.util.Util;

import java.sql.SQLException;

public class GameServiceHelper {


    public void playInning (Inning inning, int inningId) throws SQLException, ClassNotFoundException {

        System.out.println("Team " + inning.getBattingTeam().getName() + " Started the match ");
        Boolean allOut = false;

        for (int i = Constants.ONE; i <= inning.getNumOfOver(); i++) {

            int currentBowlerIndex = Util.getRandomBowler();
            if (allOut || (inning.isChaser() && (inning.getScoreToChase() < Util.getScoreOfInning(inning)))) {
                return ;
            }

            String bowlerName = inning.getBowlingTeam().getPlayers().get(currentBowlerIndex).getPlayerName();
            System.out.println("Playing Over: " + i + " Bowler is: " + bowlerName);

            OverDetails overDetails = new OverDetails();
            inning.setOverDetails(overDetails);

            int overDetailsId = OverDetailsRepository.insertOverDetails(inningId, bowlerName, inning.getBowlingTeam().getName());

            inning.getOverDetails().get(i-1).setBowler(inning.getBowlingTeam().getPlayers().get(currentBowlerIndex));

            for (int j = Constants.ONE; j <= Constants.totalBallInOver; j++) {
                if(Constants.totalPlayerInTeam-1 == Util.getTotalWicketOut(inning) ) {
                    allOut = true;
                }
                if (allOut || (inning.isChaser() && (inning.getScoreToChase() < Util.getScoreOfInning(inning) ))) {
                    break;
                }
                BallDetails ballDetails = handleBall( inning, j, currentBowlerIndex);
                inning.getOverDetails().get(i-1).getBallDetails().add(ballDetails);

                BallDetailsRepository.insertBallDetails(ballDetails, overDetailsId, inning.getBattingTeam().getName(), inningId);

            }

            inning.strike.changeStrikeOnOver();
        }

    }


    BallDetails handleBall( Inning inning, int currentBall, int currentBowlerIndex) {

        BallDetails ballDetails = new BallDetails();
        int runs = Util.getRandomRun() ;

        ballDetails.setStrikerOnBall(inning.getBattingTeam().getPlayers().get(inning.strike.getcurrentStriker()));

        if (runs < 7) {
            System.out.println(currentBall + " : " + runs + " Runs by " + ballDetails.getStrikerOnBall().getPlayerName());
            inning.strike.changeStrikeOnRun(runs);

            ballDetails.setScoreOnBall(runs);
            ballDetails.setBallType(BallType.RUN);
            return  ballDetails;
        }

        ballDetails.setScoreOnBall(0);
        ballDetails.setBallType(BallType.WICKET);

        int outPlayer = inning.strike.changeStrikeOnWicket();
        Player p = inning.getBattingTeam().getPlayers().get(outPlayer);
        System.out.println("Player " + inning.getBattingTeam().getPlayers().get(outPlayer).getPlayerName() + " Out with " + Util.getPlayerWiseScore(p,inning) );

        return  ballDetails ;

    }

}