package com.company.entity;

enum PlayerType{
    BATSMAN,
    BOWLER
}

public class Player {
    private String playerName ;
    private int score ;
    private PlayerType playerType;
    private  int ballsPlayed ;

    Player(String name, String type) {
        this.score = 0;
        this.playerName = name;
        //this.playerType = type;
        this.ballsPlayed = 0;

        if(type.equals("BATSMAN")) {
            playerType = PlayerType.BATSMAN;
        } else {
            playerType = PlayerType.BOWLER;
        }
    }

    public String getName() {
        return playerName;
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

    void increaseBallsPlayed() {
        this.ballsPlayed += 1;
    }

    @Override
    public  String toString() {
        return String.format("%s: %d runs in %d balls ", playerName, score,ballsPlayed);
    }


}
