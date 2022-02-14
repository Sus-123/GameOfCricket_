package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.*;

public class MainService {

    private  static final Scanner sc = new Scanner(System.in);

    GameUtil obj = new GameUtil();

    public Game initializeNewGame() {
        System.out.println("Enter Number Of overs to be played, it can be between 0 to 50");
        int numOfOver = obj.getIntegerInput(1,50);
        System.out.println("Enter Number of Players: ");
        int numOfPlayer = obj.getIntegerInput(2,11);

        System.out.println("Enter Team1 name: ");
        String team1Name = obj.getValidStringType();
        List<String> team1PlayersName = new ArrayList<String>();
        List<String> team1PlayersType = new ArrayList<String>();
        initializeTeamPlayer(team1PlayersName, team1PlayersType,numOfPlayer);


        System.out.println("Enter Team2  name: ");
        String team2Name = obj.getValidStringType();
        List<String> team2PlayersName = new ArrayList<String>();
        List<String> team2PlayersType = new ArrayList<String>();
        initializeTeamPlayer(team1PlayersName, team2PlayersType, numOfPlayer);

        Game newGame = new Game(numOfOver, team1Name, team1PlayersName, team1PlayersType, team2Name, team2PlayersName, team2PlayersType);
        return newGame;

    }

    public void initializeTeamPlayer(List<String> playersName, List<String> playersType, int numberOfPlayer) {
        //List<String> acceptablePlayerTypes = Arrays.asList(new String[]{"BATSMAN","BOWLER"});
        //List<String> typesToBeaccepted = Arrays.asList(new String[]{"BT,BW"});
        //ArrayList<String> typesToBeAccepted = new ArrayList<>();
        for (int i = 0; i < numberOfPlayer; i++) {
            System.out.print("Player-" + i + " Name:");
            playersName.add(obj.getValidStringType());
            System.out.print("Player-" + i + " Type:");
            playersType.add(obj.getPlayerType());
        }




    }

}
