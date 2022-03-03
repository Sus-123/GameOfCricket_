package com.company.main;
import com.company.util.Util;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Util util = new Util();
        System.out.println("Press 1 - Start a new match Press 2 - view result of previously played match ");

        int choice = util.getIntegerInput(1,3);

        switch (choice) {
            case 1:
                MatchController.startNewMatch();
                break;
            case 2:
                MatchController.displayPreviouslyPlayedMatch();
                break;
            default:
                System.out.println("Need to press a key !");
        }


    }
}

