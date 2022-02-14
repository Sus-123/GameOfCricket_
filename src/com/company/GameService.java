package com.company;

import org.jetbrains.annotations.NotNull;
import java.security.PrivateKey;

public class GameService {
    private int noOfOvers ;

    public void startInning (Team teamBatsMan, Team teamBowler, int noOfOvers) {

        this.noOfOvers = noOfOvers;
        System.out.println(teamBatsMan.getName() + "will start batting");

        playInning ( teamBatsMan, false, 0);

        int scoreToChase = teamBatsMan.getTeamScore();
        playInning (teamBowler, true, scoreToChase);
        //utilObj = new GameUtil();
    }

    void playInning (Team team, boolean isChaser ,int scoreToChase) {

        Strike strike = new Strike();

        Boolean allOut = false;


        for (int i = 1; i <= noOfOvers; i++) {

            System.out.println("Playing Over: " + i);

            for (int j = 1; j <=6 ; j++) {

                allOut = playBall(team , j);

                if (allOut || (isChaser && (scoreToChase < team.getTeamScore()))) {
                    break;
                }
            }
            if (allOut || (isChaser && (scoreToChase < team.getTeamScore()))) {
                break;
            }
            strike.overChanged();
        }
    }

    boolean playBall(Team team, int ball) {
        Strike strike = new Strike();
        GameUtil utilObj = new GameUtil();
        int currentPlayer = strike.getCurrentStrike();
        int runs = utilObj.getRandomRun() ;
        team.increaseBallsPlayed(currentPlayer);

        if (runs < 7) {
            System.out.println(ball + ": " + runs + "Runs by " + team.getCurrentPlayerName(currentPlayer));
            team.increaseTeamScore(runs,currentPlayer);
            strike.changeStrike(runs);
            return  false ;
        }

        int outPlayer = strike.changeStrikeOnWicket();
        System.out.println("Player " + team.getCurrentPlayerName(outPlayer) + "Out with " + team.getPlayerScore(outPlayer) );
        team.increaseWicket();

        if(team.getTotalPlayerInTeam()-1 == team.getCurrentWicket()) return true;
        return  false ;

    }

}
