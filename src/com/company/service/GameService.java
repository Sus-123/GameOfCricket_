package com.company.service;
import com.company.Constants;
import com.company.entity.Inning;
import com.company.entity.Strike;
import com.company.entity.Team;
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



//    public void showFinalScoreBoard () {
//        System.out.println("Score Board : ");
//
//        if(inning1.score > inning2.score) {
//            System.out.println(inning1.battingTeam.getName() + "Won the Match by : " + (inning1.score - inning2.score));
//        } else {
//            System.out.println(inning2.battingTeam.getName() + "Won the Match by : " + (inning2.score - inning1.score));
//        }
//
//        System.out.println();
//        System.out.println("Score Details are: ");
//
//        showDetailsOfInning(inning1);
//        showDetailsOfInning(inning2);
//
//    }
//
//
//    public void showDetailsOfInning (Inning inning) {
//        System.out.println("--------------------------------------------");
//        System.out.println(inning.battingTeam.getName() + " : " + inning.score);
//        System.out.println();
//
//        System.out.println("Batsman Scores : ");
//        for (int i = 0; i <= inning.getCurrentWicket(); i++) {
//            String playerName = inning.battingTeam.players.get(i).getPlayerName();
//            int playerScore = inning.playersScore.get(i);
//            int playerBallsPlayed = inning.ballsPlayed.get(i);
//            System.out.println(playerName + " : " + playerScore + " On Ball : " + playerBallsPlayed);
//        }
//
//        System.out.println("Bowlers Wickets : ");
//        for (int i = Constants.lowerBowlerIndex; i < Constants.upperBowlerIndex ; i++) {
//            String bowlerName = inning.bowlingTeam.players.get(i).getPlayerName();
//            int wicketTaken = inning.bowlerWicketTaken.get(i);
//            System.out.println(bowlerName + " Wicket : " + wicketTaken);
//        }
//    }





}
