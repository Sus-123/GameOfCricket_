package com.company;

public class Constants {
    private int lowerTossBound;
    private int upperTossBound;
    private int lowerRunBound;
    private int upperRunBound;
    private int numOfPlayer;

    public Constants () {
        lowerTossBound = 0;
        upperTossBound = 2;
        lowerRunBound = 0;
        upperRunBound = 8;
        numOfPlayer = 11;
    }

    public int getLowerTossBound() {
        return lowerTossBound;
    }

    public int getUpperTossBound() {
        return upperTossBound;
    }

    public int getLowerRunBound() {
        return lowerRunBound;
    }

    public int getUpperRunBound() {
        return upperRunBound;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }
}
