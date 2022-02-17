package com.company.service;
import com.company.Constants;
import com.company.entity.BallDetails;
import com.company.entity.Inning;
import com.company.entity.OverDetails;
import com.company.entity.Strike;
import com.company.util.Util;

public class GameServiceHelper {
    Strike strike ;
    Inning inning;

    public void playInning (Inning inning) {
        System.out.println("Team " + inning.battingTeam.getName() + " Started the match ");
        strike = new Strike();
        this.inning = inning;

        Boolean allOut = false;

        for (int i = Constants.ONE; i <= inning.numOfOver; i++) {
            System.out.println("Playing Over: " + i);
            OverDetails overDetails = new OverDetails();
            overDetails.bowler = inning.bowlingTeam.players.get(inning.getCurrentBowler); // getCurrentBowler has to be implemented.

            //playing each ball
            for (int j = Constants.ONE; j <= Constants.totalBallInOver; j++) {

                // for each ball there will be a new instance of ballDetails.
                BallDetails ballDetails = new BallDetails();
                inning.increaseCurrentBall();
                inning.currentBatsMan = strike.getCurrentStrike();
                allOut = playBall(ballDetails);

                //add particular ball details, after ball being played into over details
                overDetails.ballDetails.add(ballDetails);

                if (allOut || (inning.isChaser && (inning.scoreToChase < inning.getScore()))) {
                    if(allOut)
                        break;
                    else {
                        System.out.println(inning.battingTeam.getName() + " Won the Match by: " + (inning.getScore() - inning.scoreToChase) + " run ");
                        break;
                    }
                }
            }
            if (allOut || (inning.isChaser && (inning.scoreToChase < inning.getScore()))) {
                if(allOut)
                    break;
                else {
                    System.out.println(inning.battingTeam.getName() + " Won the Match by: " + (inning.getScore() - inning.scoreToChase) + " run ");
                    break;
                }
            }
            strike.changeStrikeOnOver();
        }

    }


    boolean playBall(BallDetails ballDetails) {

        int runs = Util.getRandomRun() ;

        //setting two parameters of this particular ball : batsman and striker.
        ballDetails.batsmanOnBall = inning.battingTeam.players.get(inning.currentBatsMan);
        ballDetails.strikerOnBall = inning.battingTeam.players.get(strike.getCurrentStrike());

        //player hit a run
        if (runs < 7) {
            System.out.println(inning.currentBall + ": " + runs + " Runs by " + inning.getPlayerName(inning.currentBatsMan));
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

        int outPlayer = strike.changeStrikeOnWicket();
        System.out.println("Player " + inning.getPlayerName(outPlayer) + " Out with " + inning.getPlayerScore(outPlayer) );
        inning.increaseWicket();

        if(Constants.totalPlayerInTeam-1 == inning.getCurrentWicket()) return true;
        return  false ;
    }

}
