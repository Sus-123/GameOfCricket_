package com.company.entity.matchEntity;
import com.company.constants.Constants;

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
    public PlayerType getPlayerType () {
        return this.playerType;
    }

    public String getPlayerTypeInString () {
        if(playerType == PlayerType.BATSMAN)
            return  "BATSMAN";
        return "BOWLER";
    }


}
