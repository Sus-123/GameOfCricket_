package com.company.entity.matchEntity;
import com.company.constants.Constants;
import lombok.Data;

import java.util.ArrayList;

@Data
public class Team {

    private  String teamName;
    private ArrayList<Player> players;

    public Team(String teamName) {
        this.teamName = teamName;
        players = new ArrayList<>();
        setTeamPlayers(teamName);
    }

    private void setTeamPlayers(String teamName) {
        for (int i = 0; i < Constants.totalPlayerInTeam; i++) {
            String playerName = (teamName + (i+1));
            PlayerType playerType;
            if(i < Constants.MaxBatsmanInTeam)
                playerType = PlayerType.BATSMAN;
            else
                playerType = PlayerType.BOWLER;
            Player p = new Player(playerName, playerType);
            players.add(p);
        }
    }





}
