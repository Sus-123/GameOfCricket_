package com.company.service;
import com.company.Constants;
import com.company.Game;
import com.company.util.GameUtil;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

public class MainService {

    GameUtil gameUtil = new GameUtil();
    Constants constants = new Constants();

    /**
     * initializeNewGame : Will create a new game, taking user input for num of overs, each team details
     */
    public Game initializeNewGame() {
        System.out.println("Enter Number Of overs to be played, it can be between 0 to 50");
        int numOfOver = gameUtil.getIntegerInput(1,50);
        //System.out.println("Enter Number of Players: ");
        //int numOfPlayer = gameUtil.getIntegerInput(2,11);
        int numOfPlayer = constants.getNumOfPlayer();

        System.out.println("Enter Team1 name: ");
        String team1Name = gameUtil.getValidStringType();
        List<String> team1PlayersName = new ArrayList<String>();
        List<String> team1PlayersType = new ArrayList<String>();
        initializeTeamPlayer(team1PlayersName, team1PlayersType,numOfPlayer);


        System.out.println("Enter Team2  name: ");
        String team2Name = gameUtil.getValidStringType();
        List<String> team2PlayersName = new ArrayList<String>();
        List<String> team2PlayersType = new ArrayList<String>();
        initializeTeamPlayer(team2PlayersName, team2PlayersType, numOfPlayer);

        Game newGame = new Game(numOfOver, team1Name, team1PlayersName, team1PlayersType, team2Name, team2PlayersName, team2PlayersType);
        return newGame;

    }


    /**
     * initializeTeamPlayer : will instantiate players of each team.
     * @param playersName : Name of each player
     * @param playersType : Type of each player
     */
    public void initializeTeamPlayer(List<String> playersName, List<String> playersType, int numberOfPlayer) {
        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.print("Player-" + i + " Name:");
            playersName.add(gameUtil.getValidStringType());
            System.out.print("Player-" + i + " Type:");
            playersType.add(gameUtil.getPlayerType());
        }
        //System.out.println("Size of team name" + playersName.size());
       // System.out.println("size of type :" + playersType.size());

    }

}
