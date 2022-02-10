package com.company;

public class Player {
    private String name ;
    private int score ;
    private  int ballsPlayed ;

    Player(String name) {
        this.name = name;
        this.score = 0;
        this.ballsPlayed = 0;
    }
    public String getName() {
        return name;
    }
    public  int getScore() {
        return  score;
    }
    public int getBallsPlayed() {
        return ballsPlayed;
    }
    void incrementScore(int score) {
        this.score += score;
    }
    void incrementBallsPlayed() {
        this.ballsPlayed += 1;
    }
}
