package com.company.entity;
import java.util.ArrayList;

public class Inning {

    private Team battingTeam;
    private Team bowlingTeam;
    private boolean isChaser;
    private int scoreToChase;

    private int numOfOver;

    public Strike strike;

    private ArrayList<OverDetails> overDetails;


    public Inning (Team battingTeam, Team ballingTeam, boolean isChaser, int scoreToChase, int numOfOver, Strike strike) {

        this.battingTeam = battingTeam;
        this.bowlingTeam = ballingTeam;
        this.isChaser = isChaser;
        this.scoreToChase = scoreToChase;
        this.numOfOver = numOfOver;
        this.strike = strike;
        this.overDetails = new ArrayList<>();


    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return  bowlingTeam;
    }

    public boolean isChaser () {
        return  this.isChaser;
    }

    public int getScoreToChase () {
        return scoreToChase;
    }

    public  int getNumOfOver () {
        return numOfOver;
    }

    public ArrayList<OverDetails> getOverDetails () {
        return  this.overDetails;
    }

    public void setOverDetails (OverDetails overs) {
        overDetails.add(overs);
    }

    public void setOverDetailsArr ( ArrayList<OverDetails> overDetails) {
        this.overDetails = overDetails;
    }





}
