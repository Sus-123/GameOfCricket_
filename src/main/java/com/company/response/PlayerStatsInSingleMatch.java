package com.company.response;

public class PlayerStatsInSingleMatch {

    private String name;
    private BattingStatsOfPlayer battingStatsOfPlayer;
    private BowlingStatsOfPlayer bowlingStatsOfPlayer;


    public PlayerStatsInSingleMatch(String playerName, BattingStatsOfPlayer battingStatsOfPlayer, BowlingStatsOfPlayer bowlingStatsOfPlayer) {
        this.name = playerName;
        this.battingStatsOfPlayer = battingStatsOfPlayer;
        this.bowlingStatsOfPlayer = bowlingStatsOfPlayer;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getName () {
        return this.name;
    }

    public BattingStatsOfPlayer getBattingStatsOfPlayer() {
        return battingStatsOfPlayer;
    }

    public void setBattingStatsOfPlayer(BattingStatsOfPlayer battingStatsOfPlayer) {
        this.battingStatsOfPlayer = battingStatsOfPlayer;
    }

    public BowlingStatsOfPlayer getBowlingStatsOfPlayer() {
        return bowlingStatsOfPlayer;
    }

    public void setBowlingStatsOfPlayer(BowlingStatsOfPlayer bowlingStatsOfPlayer) {
        this.bowlingStatsOfPlayer = bowlingStatsOfPlayer;
    }
}
