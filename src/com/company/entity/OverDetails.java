package com.company.entity;

import java.util.ArrayList;

public class OverDetails {
    public  Player bowler;
    public ArrayList<BallDetails> ballDetails;
    public int overNumberInSequence;

    public OverDetails() {
        ballDetails = new ArrayList<>();
    }

}
