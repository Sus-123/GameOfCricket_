package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Team {
    private  String teaName;
    private int teamScore;
    private int totalBallsPlayed;
    private int totalAvailableBalls;
    private int currentPlayer;
    ArrayList<Player> players = new ArrayList<Player>();
    Scanner sc = new Scanner(System.in);

    Team(int balls) {
        totalAvailableBalls = balls;
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
        teaName = name;
        //totalRemainingBalls = 0;
        currentPlayer = 0;
    }

    //getters
    public String getName() {
        return  this.teaName;
    }
    public int getTeamScore() {
        return teamScore ;
    }
    public int getTotalBallsPlayed() {
        return  this.totalBallsPlayed;
    }
    public int getTotalAvailableBalls () {
        return  this.totalAvailableBalls;
    }
    public Player getCurrentPlayerData() {
        return  this.players.get(currentPlayer);
    }

    void increaseTeamScore(int score) {
        teamScore += score;
        Player p = players.get(currentPlayer);
        p.incrementScore(score);
    }

    void increaseTotalBallsPlayed() {
        this.totalBallsPlayed++;
        Player p = players.get(currentPlayer);
        p.incrementBallsPlayed();
    }

    int getTotalWicket() {
        return  currentPlayer;
    }

    void wicketOut() {
        currentPlayer++;
    }

    void printTotalPlayersScore() {
        //System.out.println(String.format("%s: %d runs in %d balls", p.getName(), p.getScore(), p.getBallsPlayed()));

        System.out.println(String.format("%s team members scores are : ", this.teaName));
        for (int i = 0; i <= currentPlayer; i++) {
            Player p = players.get(i);
            System.out.println( String.format("%s: %d runs in %d balls", p.getName(), p.getScore(), p.getBallsPlayed()) );
        }
    }


}
