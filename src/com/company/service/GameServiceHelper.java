package com.company.service;
import com.company.Constants;
import com.company.database.DbOperations;
import com.company.entity.*;
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

            int overDetailsId = DbOperations.insertOverInDb(inningId, bowlerName, inning.getBowlingTeam().getName());

            //set bowler for this inning
            inning.getOverDetails().get(i-1).setBowler(inning.getBowlingTeam().getPlayers().get(currentBowlerIndex));

            //playing each ball
            for (int j = Constants.ONE; j <= Constants.totalBallInOver; j++) {
                if(Constants.totalPlayerInTeam-1 == Util.getTotalWicketOut(inning) ) {
                    allOut = true;
                }
                if (allOut || (inning.isChaser() && (inning.getScoreToChase() < Util.getScoreOfInning(inning) ))) {
                    break;
                }
                BallDetails ballDetails = playBall( inning, j, currentBowlerIndex);
                //add particular ball details, after ball being played into over details
                inning.getOverDetails().get(i-1).getBallDetails().add(ballDetails);

                // add into db
                DbOperations.insertBallDetailsInDb(ballDetails, overDetailsId, inning.getBattingTeam().getName());

            }

            inning.strike.changeStrikeOnOver();
        }
    }


    BallDetails playBall( Inning inning, int currentBall, int currentBowlerIndex) {

        BallDetails ballDetails = new BallDetails();
        int runs = Util.getRandomRun() ;

        //setting  parameters of this particular ball : striker.
        ballDetails.setStrikerOnBall(inning.getBattingTeam().getPlayers().get(inning.strike.getcurrentStriker()));

        //player hit a run
        if (runs < 7) {
            System.out.println(currentBall + " : " + runs + " Runs by " + ballDetails.getStrikerOnBall().getPlayerName());
           // inning.increaseScore(runs);
            inning.strike.changeStrikeOnRun(runs);

            //setting the parameters  e.g.score,type for this particular ball.
            ballDetails.setScoreOnBall(runs);
            ballDetails.setBallType(BallType.RUN);
            return  ballDetails;
        }

        // score , type will be different, if players get out.
        ballDetails.setScoreOnBall(0);
        ballDetails.setBallType(BallType.WICKET);

        int outPlayer = inning.strike.changeStrikeOnWicket();
        Player p = inning.getBattingTeam().getPlayers().get(outPlayer);
        System.out.println("Player " + inning.getBattingTeam().getPlayers().get(outPlayer).getPlayerName() + " Out with " + Util.getPlayerWiseScore(p,inning) );

        return  ballDetails ;

    }

}
