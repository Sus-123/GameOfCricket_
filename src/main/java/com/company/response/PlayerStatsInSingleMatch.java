package com.company.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@AllArgsConstructor
public class PlayerStatsInSingleMatch {

    private String name;
    private BattingStatsOfPlayer battingStatsOfPlayer;
    private BowlingStatsOfPlayer bowlingStatsOfPlayer;

}
