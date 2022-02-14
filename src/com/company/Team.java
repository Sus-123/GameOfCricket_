package com.company;
import java.util.ArrayList;
import java.util.*;

public class Team {

    private  String teamName;
    ArrayList<Player> players = new ArrayList<Player>();
    private  final int  TOTAL_PLAYER_IN_TEAM;
    private int teamScore;
    private int currentWicket;

    //private int totalBallsPlayed;

    Team(String teamName, List<String> playerNames, List<String> playerTypes) {
        this.teamName = teamName;
        //this.totalBallsPlayed = 0;
        this.teamScore = 0;
        this.currentWicket = 0;
        TOTAL_PLAYER_IN_TEAM = playerNames.size();
        setTeamPlayers(playerNames, playerTypes);
    }

    void setTeamPlayers(List<String> playerNames, List<String> playerTypes) {
        //System.out.println(playerNames.size());
       // System.out.println(playerTypes.size());
        for (int i = 0; i < playerNames.size() ; i++) {
            //System.out.println(playerNames.size() + playerTypes.size());
            Player p = new Player(playerNames.get(i), playerTypes.get(i));
            players.add(p);
        }
    }

    public String getName() {
        return  this.teamName;
    }

    public int getTeamScore() {
        return teamScore ;
    }

    /*public ArrayList<Player> getPlayers() {
        return players;
    }*/

    public  int getTotalPlayerInTeam() {
        return  TOTAL_PLAYER_IN_TEAM;
    }

    public int getCurrentWicket() {
        return  currentWicket;
    }

    public void increaseTeamScore(int score, int playerNumber) {
        teamScore += score;
        Player p = players.get(playerNumber);
        p.incrementScore(score);
    }

    public void increaseWicket() {
        currentWicket++;
    }

    public String getCurrentPlayerName(int currentPlayer) {
        return players.get(currentPlayer).getName();
    }

    //    public void increaseBallsPlayed (int playerNumber) {
//        this.totalBallsPlayed++;
//        Player p = players.get(playerNumber);
//        p.increaseBallsPlayed();
//    }

    public void showPlayerWiseScore() {
        for (int i = 0; i <= TOTAL_PLAYER_IN_TEAM; i++) {
            Player p = players.get(i);
            System.out.println(p);
        }
    }

    public int getPlayerScore ( int playerNumber) {
        return players.get(playerNumber).getScore();
    }

}
