package com.company.entity;
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







//package com.company.entity;
//
//import java.util.ArrayList;
//
//public class Strike {
//
//    private ArrayList<Integer> strikerArray;// will Hold just two index array which will be updated with batsman getting out.
//    private int currentStriker ;
//
//
//    /**
//     * Strike : Maintain the current players strike on the field
//     * strikeHolder : array of size two to hold player count of striker and batsman
//     *  currentStriker : can be 0 and 1 depend on which index player is batting and which one is balling
//     */
//
//    public Strike() {
//        this.currentStriker = 0 ;
//        strikerArray = new ArrayList<>();
//        strikerArray.set(0,0);
//        strikerArray.set(1,1);
//    }
//
//
//    public int getcurrentStriker () {
//        return  strikerArray.get(currentStriker);
//    }
//
//    public void changeStrikeOnRun (int runs) {
//        if(runs%2 == 1)
//            currentStriker = (currentStriker+1)%2;
//    }
//
//    public void changeStrikeOnOver () {
//        currentStriker = (currentStriker+1)%2;
//    }
//
//    public int changeStrikeOnWicket () {
//        int maxIndex = Integer.max(strikerArray.get(0), strikerArray.get(1));
//        //Now next player will be the one who has just a greater number than the max Players index.
//        int playerBold = strikerArray.get(currentStriker);
//        strikerArray.set(currentStriker,maxIndex+1) ;
//        return  playerBold;
//    }
//}
