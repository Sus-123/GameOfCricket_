package com.company.response;

public class BowlingStatsOfPlayer {

    private int wickets;
    private int ballsBowled;



    public BowlingStatsOfPlayer(int wicketTaken, int ballsBowled) {
        this.wickets = wicketTaken;
        this.ballsBowled= ballsBowled;
    }


    public int getWickets() {
        return wickets;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }


    public void setBallsBowled(int ballsBowled) {
        this.ballsBowled = ballsBowled;
    }
}
