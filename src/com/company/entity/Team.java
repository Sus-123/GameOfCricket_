package com.company.entity;

import com.company.Constants;
import java.util.ArrayList;


public class Team {

    private  String teamName;
    ArrayList<Player> players;

    public Team(String teamName) {
        this.teamName = teamName;
        players = new ArrayList<>();
        setTeamPlayers(teamName);
    }

    void setTeamPlayers(String teamName) {
        for (int i = 0; i < Constants.totalPlayerInTeam; i++) {
            String playerName = (teamName + (i+1));
            String playerType = "BATSMAN";

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

    public String getCurrentPlayerName (int currentPlayerIndex) {
        return players.get(currentPlayerIndex).getPlayerName();
    }




//    public void showPlayerWiseScore() {
//        for (int i = 0; i <= Constants.totalPlayerInTeam; i++) {
//            Player p = players.get(i);
//            System.out.println(p);
//        }
//    }


}
