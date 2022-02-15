package com.company;


import com.company.entity.Team;
import com.company.service.GameService;
import com.company.util.GameUtil;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

/*
enum Winner{
    TEAMFIRST,
    TEAMSECOND,
    TIE,
    STARTED
} */

public class Game {

    private  int noOfOvers;
    private Team teamFirst;
    private  Team teamSecond;
    //private Winner winner;

    GameUtil UtilObj = new GameUtil();
    GameService serviceObj = new GameService();

     public Game( int noOfOvers,
          String team1Name,
          List<String> team1PlayersName,
          List<String> team1PlayersType,
          String team2Name,
          List<String> team2PlayersName,
          List<String> team2PlayersType
    ){
        this.noOfOvers = noOfOvers;
        this.teamFirst = new Team(team1Name, team1PlayersName, team1PlayersType);
        this.teamSecond = new Team(team2Name, team2PlayersName, team2PlayersType);
       // winner = Winner.STARTED;
    }


    public void startGame () {
        int toss = UtilObj.playToss();
        if(toss == 0)
            serviceObj.startInning(teamFirst,teamSecond, noOfOvers);
        else
            serviceObj.startInning(teamSecond, teamFirst, noOfOvers);
    }

    public void showFinalScoreBoard () {
    }

    public void declareWinner () {
    }


}
