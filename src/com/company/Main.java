package com.company;
import java.util.Scanner;

public class Main {
    private  static final  Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        MainService obj = new MainService();

        Game newGame = obj.initializeNewGame();

        newGame.startGame();

        newGame.showFinalScoreBoard();

    }
}

