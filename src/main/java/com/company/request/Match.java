package com.company.request;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Match {
    int overs;
    @JsonProperty("first_team_name")
    String TeamAName;
    @JsonProperty("second_team_name")
    String TeamBName;
    @JsonProperty("match_name")
    String matchName;

}
