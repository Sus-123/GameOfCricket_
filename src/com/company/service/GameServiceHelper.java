package com.company.service;
import com.company.Constants;
import com.company.entity.BallDetails;
import com.company.entity.Inning;
import com.company.entity.OverDetails;
import com.company.entity.Strike;
import com.company.util.Util;

public class GameServiceHelper {



    public void playInning (Inning inning) {
        System.out.println("Team " + inning.battingTeam.getName() + " Started the match ");
        Strike strike = new Strike();

        Boolean allOut = false;

        for (int i = Constants.ONE; i <= inning.numOfOver; i++) {

            System.out.println("Playing Over: " + i);

            //new overDetails will be initialised with each over
            OverDetails overDetails = new OverDetails();

            int currentBowlerIndex = Util.getRandomBowler();
            overDetails.bowler = inning.bowlingTeam.players.get(currentBowlerIndex);

            //playing each ball
            for (int j = Constants.ONE; j <= Constants.totalBallInOver; j++) {

                // for each ball there will be a new instance of ballDetails.
                BallDetails ballDetails = new BallDetails();
                inning.currentBatsMan = strike.getCurrentStrike();
                allOut = playBall( inning, ballDetails, j, currentBowlerIndex, strike);

                //add particular ball details, after ball being played into over details
                overDetails.ballDetails.add(ballDetails);

                if (allOut || (inning.isChaser && (inning.scoreToChase < inning.getScore()))) {
                   break;
                }
            }

            if (allOut || (inning.isChaser && (inning.scoreToChase < inning.getScore()))) {
               break;
            }

            strike.changeStrikeOnOver();
            inning.overDetails.add(overDetails);
        }

    }


    boolean playBall( Inning inning, BallDetails ballDetails, int currentBall, int currentBowlerIndex, Strike strike) {

        int runs = Util.getRandomRun() ;

        //setting two parameters of this particular ball : batsman and striker.
        ballDetails.batsmanOnBall = inning.battingTeam.players.get(inning.currentBatsMan);
        ballDetails.strikerOnBall = inning.battingTeam.players.get(strike.getCurrentStrike());

        //player hit a run
        if (runs < 7) {
            System.out.println(currentBall + ": " + runs + " Runs by " + inning.getPlayerName(inning.currentBatsMan));
            inning.increaseScore(runs);
            strike.changeStrikeOnRun(runs);

            //setting the parameters  e.g.score,type for this particular ball.
            ballDetails.scoreOnBall = runs;
            ballDetails.isWicket = false;
            return  false ;
        }

        // score , type will be different, if players get out.
        ballDetails.scoreOnBall = 0;
        ballDetails.isWicket = true;

        //inning.increaseBowlerWicketTaken();
        inning.bowlerWicketTaken.set(currentBowlerIndex, inning.bowlerWicketTaken.get(currentBowlerIndex)+1);

        int outPlayer = strike.changeStrikeOnWicket();
        System.out.println("Player " + inning.getPlayerName(outPlayer) + " Out with " + inning.getPlayerScore(outPlayer) );
        inning.increaseWicket();

        if(Constants.totalPlayerInTeam-1 == inning.getCurrentWicket()) return true;
        return  false ;

    }

}
