package com.company.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BattingStatsOfPlayer {


    private int runsScored;
    private int centuries;
    private int sixes;
    private int fours;
    private int ballsPlayed;


}
