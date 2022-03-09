package com.company.entity.requestEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {

    int overs;
    @JsonProperty("first_team_name")
    String TeamAName;
    @JsonProperty("second_team_name")
    String TeamBName;
    @JsonProperty("match_name")
    String matchName;

    public int getOvers() {
        return overs;
    }

    public String getTeamAName() {
        return TeamAName;
    }

    public String getTeamBName() {
        return TeamBName;
    }

    public void setOvers(int overs) {
        this.overs = overs;
    }

    public void setTeamAName(String teamAName) {
        TeamAName = teamAName;
    }

    public void setTeamBName(String teamBName) {
        TeamBName = teamBName;
    }

    public String getMatchName() {
        return matchName;
    }

    public void setMatchName(String matchName) {
        this.matchName = matchName;
    }

}
