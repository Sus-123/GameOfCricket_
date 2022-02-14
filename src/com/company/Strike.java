package com.company;
public class Strike {

    private int currentStrike ;
    private int [] strikeHolders;

    public Strike() {
        this.currentStrike = 0 ;
        this.strikeHolders = new int[]{0,1};
    }

    public int getCurrentStrike () {
        return strikeHolders[currentStrike] ;
    }

    public void changeStrike (int runs) {
        if(runs%2 == 1)
            currentStrike = (currentStrike+1)%2;
    }

    public void overChanged () {
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
