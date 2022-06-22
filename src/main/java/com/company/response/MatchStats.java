package com.company.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MatchStats {


    private String Match_Name;
    private TeamStats teamAStats;
    private TeamStats teamBStats;
    private int overs;
    private String winningTeam;

}

