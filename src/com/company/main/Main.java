package com.company.main;
import com.company.Constants;
import com.company.entity.Team;
import com.company.service.GameService;
import com.company.util.Util;

public class Main {
    public static void main(String[] args) {

        Util util = new Util();
        GameService gameService = new GameService();

        System.out.println("Enter Number Of overs to be played, it can be between 1 to 50");
        int numOfOver = util.getIntegerInput(Constants.loweBoundOfOver,Constants.upperBoundOfOver);

        System.out.println("Enter Team1 name: ");
        String team1Name = util.getValidStringType();
        Team team1 = new Team(team1Name);


        System.out.println("Enter Team2  name: ");
        String team2Name = util.getValidStringType();
        Team team2 = new Team(team2Name);
       // System.out.println(team1.getName() + team2.getName());
        gameService.initializeNewGame(team1,team2,numOfOver);


        //Creating a new match
       // gameServiceHelper.initializeNewGame();

        //see final score board
    //    gameServiceHelper.showFinalScoreBoard();

    }
}

