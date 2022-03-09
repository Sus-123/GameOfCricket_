package com.company.entity.responseEntity;

public class BattingStatsOfPlayer {


    private int runsScored;
    private int centuries;
    private int sixes;
    private int fours;
    private int ballsPlayed;


    public BattingStatsOfPlayer(int runsScored, int centuries, int sixes, int fours, int ballsPlayed) {
        this.runsScored = runsScored;
        this.centuries = centuries;
        this.sixes = sixes;
        this.fours = fours;
        this.ballsPlayed= ballsPlayed;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public int getCenturies() {
        return centuries;
    }

    public void setCenturies(int centuries) {
        this.centuries = centuries;
    }

    public int getSixes() {
        return sixes;
    }

    public void setSixes(int sixes) {
        this.sixes = sixes;
    }

    public int getFours() {
        return fours;
    }

    public void setFours(int fours) {
        this.fours = fours;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

}
