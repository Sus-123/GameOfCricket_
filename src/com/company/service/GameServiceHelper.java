package com.company.service;
import com.company.Constants;
import com.company.entity.BallDetails;
import com.company.entity.BallType;
import com.company.entity.Inning;
import com.company.entity.OverDetails;
import com.company.util.Util;

public class GameServiceHelper {


    public void playInning (Inning inning) {

        System.out.println("Team " + inning.getBattingTeam().getName() + " Started the match ");

        Boolean allOut = false;

        for (int i = Constants.ONE; i <= inning.getNumOfOver(); i++) {

            System.out.println("Playing Over: " + i);

            //new overDetails will be initialised with each over
            OverDetails overDetails = new OverDetails();

            int currentBowlerIndex = Util.getRandomBowler();
            overDetails.setBowler(inning.getBowlingTeam().getPlayers().get(currentBowlerIndex));

            //playing each ball
            for (int j = Constants.ONE; j <= Constants.totalBallInOver; j++) {

                // for each ball there will be a new instance of ballDetails.
                BallDetails ballDetails = playBall( inning , j, currentBowlerIndex);
                //add particular ball details, after ball being played into over details
                overDetails.getBallDetails().add(ballDetails);

                if(Constants.totalPlayerInTeam-1 == Util.getTotalWicketOut(inning) ) {
                    allOut = true;
                }




                if (allOut || (inning.isChaser() && (inning.getScoreToChase() < Util.getScoreOfInning(inning) ))) {
                   break;
                }
            }

            if (allOut || (inning.isChaser() && (inning.getScoreToChase() < Util.getScoreOfInning(inning)))) {
               break;
            }

            inning.strike.changeStrikeOnOver();
            inning.setOverDetails(overDetails);
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

        //inning.increaseBowlerWicketTaken();
       // inning.bowlerWicketTaken.set(currentBowlerIndex, inning.bowlerWicketTaken.get(currentBowlerIndex)+1);

        int outPlayer = inning.strike.changeStrikeOnWicket();
        System.out.println("Player " + inning.getBattingTeam().getPlayers().get(outPlayer).getPlayerName() + " Out with " + Util.getPlayerWiseScore(outPlayer,inning) );
        //inning.increaseWicket();


        return  ballDetails ;

    }

}
