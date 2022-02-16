package com.company.main;
import com.company.Game;
import com.company.service.MainService;


public class Main {

    public static void main(String[] args) {

        MainService mainService = new MainService();

        //Creating a new match with num of overs,two teams, its player and player types
        Game newGame = mainService.initializeNewGame();

        //start the match
        newGame.startGame();

        //see final score board
        newGame.showFinalScoreBoard();

    }
}

