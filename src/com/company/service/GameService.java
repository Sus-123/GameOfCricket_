package com.company.service;
import com.company.Constants;
import com.company.entity.Inning;
import com.company.entity.Strike;
import com.company.entity.Team;
import com.company.util.Util;

public class GameService {
    Strike strike ;
    Inning inning;
    int noOfOvers ;
    int scoreToChase ;
    boolean isChaser;

    public GameService(Team battingTeam, Team bowlingTeam, boolean isChaser, int scoreToChase, int noOfOvers) {
        this.inning = new Inning(battingTeam, bowlingTeam);
        this.noOfOvers = noOfOvers;
        this.scoreToChase = scoreToChase;
        this.isChaser = isChaser;

    }

    public int playInning () {
       // this.inning = new Inning(battingTeam, bowlingTeam);
        strike = new Strike();

        Boolean allOut = false;

        for (int i = 1; i <= noOfOvers; i++) {

            System.out.println("Playing Over: " + i);
            for (int j = 1; j <= 6; j++) {
                int currentPlayer = strike.getCurrentStrike();
                allOut = playBall(j, currentPlayer, inning.battingTeam);

                if (allOut || (isChaser && (scoreToChase < inning.getScore()))) {
                    if(allOut)
                        return inning.getScore();
                    else {
                        System.out.println(inning.battingTeam.getName() + " Won the Match by: " + (inning.getScore() - scoreToChase) + " run ");
                        return inning.getScore();
                    }
                }
            }
            if (allOut || (isChaser && (scoreToChase < inning.getScore()))) {
                if(allOut)
                    return inning.getScore();
                else {
                    System.out.println(inning.battingTeam.getName() + " Won the Match by: " + (inning.getScore() - scoreToChase) + " run ");
                    return inning.getScore();
                }
            }
            strike.changeStrikeOnOver();
        }

        return  inning.getScore();

    }

    /**
     * Play Ball will be called for each ball and score a run accordingly.
     * @param team : team from which player will play the ball
     * @param ball: ball num to be played .
     * @param currentplyr
     */

    boolean playBall(int ball ,int currentPlayer , Team team) {
        //int currentPlayer = strike.getCurrentStrike();

        int runs = Util.getRandomRun() ;
        inning.increaseBallsPlayed(currentPlayer);

        if (runs < 7) {
            System.out.println(ball + ": " + runs + " Runs by " + inning.getCurrentBatsManName(currentPlayer));
            inning.increaseScore(runs, currentPlayer);
            //inning.playersScore.set(currentPlayer, inning.playersScore.get(currentPlayer)+runs);
            //System.out.println(inning.playersScore.get(currentPlayer));
            strike.changeStrikeOnRun(runs);
            return  false ;
        }

        int outPlayer = strike.changeStrikeOnWicket();
        System.out.println("Player " + team.getCurrentPlayerName(outPlayer) + " Out with " + inning.getPlayerScore(outPlayer) );
        inning.increaseWicket();

        if(Constants.totalPlayerInTeam-1 == inning.getCurrentWicket()) return true;
        return  false ;

    }

}
