package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    private  String name;
    private int teamScore;
    private int totalBallsPlayed;
    private int totalRemainingBalls;
    private int currentPlayer;
    ArrayList<Player> players = new ArrayList<Player>();
    Scanner sc = new Scanner(System.in);

    Team() {
        System.out.println("Enter Team name : ");
        String name = sc.nextLine();

        //set players list
        System.out.println("Enter 10 Players name : ");
        for (int i = 0; i < 10; i++) {
            String playerName = sc.nextLine();
            Player p = new Player(playerName);
            players.add(p);
        }
        teamScore = 0;
        totalBallsPlayed = 0;
        totalRemainingBalls = 0;
        currentPlayer = 0;
    }

    //getters
    public String getName() {
        return  this.name;
    }
    public int getTeamScore() {
        return teamScore ;
    }
    public int getTotalBallsPlayed() {
        return  this.totalBallsPlayed;
    }
    public int getTotalRemainingBalls () {
        return  this.totalRemainingBalls;
    }
    public int getCurrentPlayer() {
        return  this.currentPlayer;
    }

    void increaseTeamScore(int score) {
        teamScore += score;
        Player p = players.get(currentPlayer);
        p.incrementScore(score);
    }

    void setTotalBallsPlayed() {
        this.totalBallsPlayed++;
        Player p = players.get(currentPlayer);
        p.incrementBallsPlayed();
    }

    void wicketOut() {
        currentPlayer++;
    }

    void printTotalPlayersScore() {
        //System.out.println(String.format("%s: %d runs in %d balls", p.getName(), p.getScore(), p.getBallsPlayed()));

        System.out.println(String.format("%s team scores are : ", this.name));
        for (int i = 0; i <= currentPlayer; i++) {
            Player p = players.get(i);
            System.out.println( String.format("%s: %d runs in %d balls", p.getName(), p.getScore(), p.getBallsPlayed()) );
        }
    }


}
