package com.company.entity;
import com.company.Constants;

public class Player {
    private String playerName ;
    private PlayerType playerType;


    public Player(String name, String type) {
        this.playerName = name;
        if(type.equals(Constants.BATSMAN)) {
            playerType = PlayerType.BATSMAN;
        } else {
            playerType = PlayerType.BOWLER;
        }
    }
    public String getPlayerName() {
        return playerName;
    }


}
