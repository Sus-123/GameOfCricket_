package com.company.service;
import com.company.Constants;
import com.company.entity.*;
import com.company.util.Util;

public class GameServiceHelper {


    public void playInning (Inning inning) {

        System.out.println("Team " + inning.getBattingTeam().getName() + " Started the match ");
        Boolean allOut = false;

        for (int i = Constants.ONE; i <= inning.getNumOfOver(); i++) {
            int currentBowlerIndex = Util.getRandomBowler();
            if (allOut || (inning.isChaser() && (inning.getScoreToChase() < Util.getScoreOfInning(inning)))) {
                return ;
            }

            System.out.println("Playing Over: " + i + " Bowler is: " + inning.getBowlingTeam().getPlayers().get(currentBowlerIndex).getPlayerName());
            OverDetails overDetails = new OverDetails();
            inning.setOverDetails(overDetails);

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
            }
            inning.strike.changeStrikeOnOver();
        }
    }


    BallDetails playBall( Inning inning, int currentBall, int currentBowlerIndex) {

        BallDetails ballDetails = new BallDetails();
        int runs = Util.getRandomRun() ;

        //setting two parameters of this particular ball : batsman and striker.
        ballDetails.setStrikerOnBall(inning.getBattingTeam().getPlayers().get(inning.strike.getCurrentStrike()));
        ballDetails.setBowlerOnBall(inning.getBowlingTeam().getPlayers().get(currentBowlerIndex));

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
