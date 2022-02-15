package com.company.main;
import com.company.Game;
import com.company.service.MainService;

import java.util.Scanner;

public class Main {
    private  static final  Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        MainService obj = new MainService();

        //Creating a new match with num of overs,two teams, its player and player types
        Game newGame = obj.initializeNewGame();

        //start the match
        newGame.startGame();

        //see final score board
        newGame.showFinalScoreBoard();

    }
}

