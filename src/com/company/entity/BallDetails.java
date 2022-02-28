package com.company.entity;

public class BallDetails {

    private BallType ballType;

    //private Player bowlerOnBall;
    private Player strikerOnBall;
    private int scoreOnBall;

    public BallDetails () {
        this.scoreOnBall = 0;
    }


    public Player getStrikerOnBall() {
        return strikerOnBall;
    }

    public void setStrikerOnBall(Player strikerOnBall) {
        this.strikerOnBall = strikerOnBall;
    }

    public int getScoreOnBall() {
        return scoreOnBall;
    }

    public void setScoreOnBall(int scoreOnBall) {
        this.scoreOnBall = scoreOnBall;
    }


    public BallType getBallType() {
        return ballType;
    }

    public void setBallType(BallType ballType) {
        this.ballType = ballType;
    }
}
