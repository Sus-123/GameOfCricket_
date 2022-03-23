package com.company.entity.matchEntity;
import lombok.Data;
import java.util.ArrayList;


@Data
public class OverDetails {
    private Player bowler;
    private ArrayList<BallDetails> ballDetails;
    public OverDetails() {
        ballDetails = new ArrayList<BallDetails>();
    }
}
