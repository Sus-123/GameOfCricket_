package com.company.entity;

import com.company.Constants;
import java.util.ArrayList;

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
            String playerType;
            if(i < Constants.MaxBatsmanInTeam)
                playerType = Constants.BATSMAN;
            else
                playerType = Constants.BOWLER;
            Player p = new Player(playerName, playerType);
            players.add(p);
        }
    }

    public String getName() {
        return  this.teamName;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }



}
