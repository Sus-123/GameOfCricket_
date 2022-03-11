package com.company.entity.responseEntity;

public class TeamStats {

    private String name;
    private int runs;
    private int wickets;
    private float ballsPlayed;


    public TeamStats( String name, int runs, int wickets, float ballsPlayed ) {
        this.name = name;
        this.runs = runs;
        this.wickets = wickets;
        this.ballsPlayed = ballsPlayed;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getRuns() {
        return runs;
    }

    public void setBallsPlayed(float oversPlayed) {
        this.ballsPlayed = oversPlayed;
    }

    public float getBallsPlayed() {
        return ballsPlayed;
    }


    public void setWickets(int wickets) {
        this.wickets = wickets;
    }


    public int getWickets() {
        return wickets;
    }




}
