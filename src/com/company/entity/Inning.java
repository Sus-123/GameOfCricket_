package com.company.entity;

import java.util.ArrayList;

public class Inning {

    public Team battingTeam;
    public Team bowlingTeam;
    public boolean isChaser;
    public int scoreToChase;
    public int numOfOver;
    public int score;
    private int currentWicket;
    public int currentBall;
    public int currentBatsMan;
    public int getCurrentBowler;
    public ArrayList<Integer> playersScore;
    public ArrayList<Integer> ballsPlayed;
    public ArrayList<OverDetails> overDetails;

    //private ArrayList<Integer> wicketTaken;

    public Inning (Team battingTeam, Team ballingTeam, boolean isChaser, int scoreToChase, int numOfOver) {

        this.battingTeam = battingTeam;
        this.bowlingTeam = ballingTeam;
        this.isChaser = isChaser;
        this.scoreToChase = scoreToChase;
        this.numOfOver = numOfOver;
        this.score = 0;
        this.currentWicket = 0;
        this.currentBall = 0;
        this.currentBatsMan = 0;
        //this.getCurrentBowler = 0;

        this.playersScore = new ArrayList<Integer>(11);
        this.ballsPlayed = new ArrayList<Integer>(11);
        for(int i = 0 ; i < 11; i++) {
            this.playersScore.add(0);
            this.ballsPlayed.add(0);
        }
    }

    public void increaseCurrentBall() {
        this.currentBall  += 1;
    }


    public int getScore () {
        return  score;
    }

    public String getPlayerName (int currentPlayerIndex) {
        return battingTeam.getCurrentPlayerName(currentPlayerIndex);
    }


    public void increaseScore(int runs) {
        this.score += runs;
        int prevScore = playersScore.get(currentBatsMan);
         //System.out.println(score + " " + prevScore);
       // System.out.println(prevScore);
        this.playersScore.set(currentBatsMan,prevScore + runs);
    }

    public int getPlayerScore (int currentPlayerIndex) {
        return playersScore.get(currentPlayerIndex);
    }

    public void increaseWicket () {
        this.currentWicket++;
    }

    public int getCurrentWicket () {
        return  this.currentWicket;
    }

//    public void increaseBallsPlayed (int currentPlayerIndex) {
//        playersScore.set(currentPlayerIndex,ballsPlayed.get(currentPlayerIndex)+1);
//    }

}
