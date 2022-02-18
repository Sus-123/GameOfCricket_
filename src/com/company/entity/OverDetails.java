package com.company.entity;
import java.util.ArrayList;

public class OverDetails {
    private Player bowler;
    private ArrayList<BallDetails> ballDetails;

    public OverDetails() {
        setBallDetails(new ArrayList<>());
    }

    public Player getBowler() {
        return bowler;
    }

    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    public ArrayList<BallDetails> getBallDetails() {
        return ballDetails;
    }

    public void setBallDetails(ArrayList<BallDetails> ballDetails) {
        this.ballDetails = ballDetails;
    }
}
