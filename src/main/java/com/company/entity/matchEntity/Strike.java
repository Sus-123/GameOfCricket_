package com.company.entity.matchEntity;
public class Strike {

    private int currentStrike ;
    private int [] strikeHolders;

    /**
     * Strike : Maintain the current players strike on the field
     * strikeHolder : array of size two to hold player count of striker and batsman
     *  currentStrike : can be 0 and 1 depend on which index player is batting and which one is balling
     */

    public Strike() {
        this.currentStrike = 0 ;
        this.strikeHolders = new int[]{0,1};
    }


    public int getcurrentStriker () {
        return strikeHolders[currentStrike] ;
    }

    public void changeStrikeOnRun (int runs) {
        if(runs%2 == 1)
            currentStrike = (currentStrike+1)%2;
    }

    public void changeStrikeOnOver () {
        currentStrike = (currentStrike+1)%2;
    }

    public int changeStrikeOnWicket () {
        int maxPlayerIndex = Integer.max(strikeHolders[0], strikeHolders[1]);
        //Now next player will be the one who has just a greater number than the max Players index.
        int outedPlayer = strikeHolders[currentStrike];
        strikeHolders[currentStrike] = maxPlayerIndex+1;
        return  outedPlayer;
    }
}

