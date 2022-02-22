package com.company.service;
import com.company.Constants;
import com.company.entity.*;
import com.company.util.Util;

public class GameService {

    GameServiceHelper gameServiceHelper = new GameServiceHelper();
    Inning inning1;
    Inning inning2;

    /**
     * instantiateNewGame  will initialise a new match, between two team
     * @param team1
     * @param team2
     * @param numOfOver : total numb of over to be played
     */

    public void initializeNewGame(Team team1, Team team2, int numOfOver) {

        if(Util.playToss() == Constants.ZERO) {
            Strike strike1 = new Strike();
            inning1 = new Inning(team1, team2, false, 0, numOfOver, strike1);
            gameServiceHelper.playInning(inning1);
            System.out.println(team1.getName() + " ended game with " + Util.getScoreOfInning(inning1));


            Strike strike2 = new Strike();
            inning2 = new Inning(team2, team1, true, Util.getScoreOfInning(inning1), numOfOver, strike2);
            gameServiceHelper.playInning(inning2);
            System.out.println(team2.getName() + " ended game with " + Util.getScoreOfInning(inning2));
        }
        else {
            Strike strike1 = new Strike();
            inning1 = new Inning(team2, team1, false, 0, numOfOver, strike1);
            gameServiceHelper.playInning(inning1);
            System.out.println(team2.getName() + " ended game with " + Util.getScoreOfInning(inning1));

            Strike strike2 = new Strike();
            inning2 = new Inning(team1, team2, true, Util.getScoreOfInning(inning1), numOfOver, strike2);
            gameServiceHelper.playInning(inning2);
            System.out.println(team1.getName() + " ended game with " + Util.getScoreOfInning(inning2));
        }
    }



    public void showFinalScoreBoard () {
        System.out.println("--------------------------------------------");
        System.out.println("--------------------------------------------");

        System.out.println("Final Result : ");
        int scoreOfInning1 = Util.getScoreOfInning(inning1);
        int scoreOfInning2 = Util.getScoreOfInning(inning2);

        if( scoreOfInning1 > scoreOfInning2 ) {
            System.out.println(inning1.getBattingTeam().getName() + "Won the Match by : " + (scoreOfInning1 - scoreOfInning2));
        } else if (scoreOfInning1 < scoreOfInning2 ){
            System.out.println(inning2.getBattingTeam().getName() + "Won the Match by : " + (scoreOfInning2 - scoreOfInning1));
        } else {
            System.out.println(" Match Draw with : " + scoreOfInning1 +" Run");
        }

        showDetailsOfInning(inning1, inning2);
        showDetailsOfInning(inning2, inning1);
    }



    public void showDetailsOfInning (Inning inning1, Inning inning2) {
        System.out.println("--------------------------------------------");
        System.out.println(inning1.getBattingTeam().getName() + " : " + Util.getScoreOfInning(inning1));
        //System.out.println("Batsman Scores : ");

        //batsman details
        System.out.println("PlayerName\t PlayerType\t  Runs\t BallsPlayed\t WicketTaken");
        for (int i = 0; i <= 10; i++) {
            Player player = inning1.getBattingTeam().getPlayers().get(i);
            System.out.println(player.getPlayerName() +"\t\t " +  player.getPlayerType() + "\t\t " + Util.getPlayerWiseScore(player,inning1) + " \t\t" + Util.getTotalBallsPlayed(player, inning1) + " \t\t" + Util.getWicketTakenByBowler(player,inning2));
        }
    }

}
