package com.company.entity.responseEntity;

public class MatchStats {

    private int id;
    private int overs;
    private TeamStats teamAStats;
    private TeamStats teamBStats;
    private String winningTeam;

    MatchStats(){

    }

    public MatchStats(int id, TeamStats teamAStats, TeamStats teamBStats, int overs,  String winningTeam){
        this.id = id;
        this.overs = overs;
        this.winningTeam = winningTeam;
        this.teamAStats = teamAStats;
        this.teamBStats = teamBStats;
    }



    public int getId() {
        return id;
    }

    public int getOvers() {
        return overs;
    }


    public String getWinningTeamId() {
        return winningTeam;
    }

    public TeamStats getTeamAStats() {
        return teamAStats;
    }

    public TeamStats getTeamBStats() {
        return teamBStats;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public void setTeamAStats(TeamStats teamAStats) {
        this.teamAStats = teamAStats;
    }

    public void setTeamBStats(TeamStats teamBStats) {
        this.teamBStats = teamBStats;
    }


    public void setWinningTeamId(int winningTeamId) {
        this.winningTeam = winningTeam;
    }

}

