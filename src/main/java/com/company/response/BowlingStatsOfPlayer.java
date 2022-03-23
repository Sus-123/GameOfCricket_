package com.company.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BowlingStatsOfPlayer {

    private int wickets;
    private int ballsBowled;

}
