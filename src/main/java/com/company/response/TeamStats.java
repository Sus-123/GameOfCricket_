package com.company.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;


@Data
@AllArgsConstructor
public class TeamStats {
    private String name;
    private int runs;
    private int wickets;
    private int ballsPlayed;

    private ArrayList<PlayerStatsInSingleMatch> playersDetails;

}
