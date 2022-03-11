package com.company.entity.responseEntity;

public class MatchStats {


    private String Match_Name;
    private int overs;
    private TeamStats teamAStats;
    private TeamStats teamBStats;
    private String winningTeam;



    public MatchStats(String matchName, TeamStats teamAStats, TeamStats teamBStats, int overs,  String winningTeam){
        this.Match_Name = matchName;
        this.overs = overs;
        this.winningTeam = winningTeam;
        this.teamAStats = teamAStats;
        this.teamBStats = teamBStats;
    }



    public String getMatch_Name() {
        return Match_Name;
    }
    public void setMatch_Name(String match_Name) {
        Match_Name = match_Name;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }
    public int getOvers() {
        return overs;
    }


    public void setTeamAStats(TeamStats teamAStats) {
        this.teamAStats = teamAStats;
    }
    public TeamStats getTeamAStats() {
        return teamAStats;
    }


    public void setTeamBStats(TeamStats teamBStats) {
        this.teamBStats = teamBStats;
    }
    public TeamStats getTeamBStats() {
        return teamBStats;
    }


    public void setWinningTeam(int winningTeamId) {
        this.winningTeam = winningTeam;
    }
    public String getWinningTeam() {
        return winningTeam;
    }









}

