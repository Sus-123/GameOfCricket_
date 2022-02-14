package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

/*
enum Winner{
    TEAMFIRST,
    TEAMSECOND,
    TIE,
    STARTED
} */

public class Game {
    private  int noOfOvers;
    private  Team teamFirst;
    private  Team teamSecond;
    //private Winner winner;

    GameUtil UtilObj = new GameUtil();
    GameService serviceObj = new GameService();

    Game( int noOfOvers,
          String team1Name,
          List<String> team1PlayersName,
          List<String> team1PlayersType,
          String team2Name,
          List<String> team2PlayersName,
          List<String> team2PlayersType
    ){
        this.noOfOvers = noOfOvers;
        Team teamFirst = new Team(team1Name, team1PlayersName, team1PlayersType);
        Team teamSecond = new Team(team2Name, team2PlayersName, team2PlayersType);
       // winner = Winner.STARTED;
    }


   /* void playInningFirst (Team team) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < overs; i++) {
            System.out.println("Over: "+(i+1));
            for (int j = 0; j < 6; j++) {

                sc.nextLine();
                int runs = ThreadLocalRandom.current().nextInt(0,7); //define in var
                team.increaseTotalBallsPlayed();

                Player p = team.getCurrentPlayerData();
                //wicket out
                if(runs == 5) {
                    System.out.println(String.format("Player %s from  %s team got bold out", p.getName(),team));
                    System.out.println(String.format("With Total run: %d , On Total balls %d: ", p.getScore(),p.getBallsPlayed()));
                    team.wicketOut();
                    int totalWicket = team.getTotalWicket();
                    if(totalWicket >= 10) {
                        System.out.println(String.format("Team, %s got complete bold out! ",  team.getName()));
                        break ;
                    }
                } else {
                    System.out.println(String.format("Wuh! Player %s scored %d: ", p.getName(),runs));
                    team.increaseTeamScore(runs);
                }

            }
            int totalWicket = team.getTotalWicket();
            if(totalWicket >= 10) {
                System.out.println(String.format("Team, %s got complete bold out! ",  team.getName()));
                break ;
            }

        }

    }*/

    public  void startGame () {
        int toss = UtilObj.playToss();
        if(toss == 0)
            serviceObj.startInning(teamFirst,teamSecond, noOfOvers);
        else
            serviceObj.startInning(teamSecond, teamFirst, noOfOvers);
    }


    public void showFinalScoreBoard () {
    }
    public void declareWinner () {
    }


}
