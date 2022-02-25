package com.company.util;
import com.company.Constants;
import com.company.entity.*;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Util {

     static UtilHelper helper = new UtilHelper();
     public  int getIntegerInput(int lower, int upper) {
        int io = helper.getIntegerInput();
        while(true) {
            try {
                //io = Integer.parseInt(sc.nextLine());
                if (io < lower || io > upper) {
                    System.out.println(String.format("Values Should be in range % d and % d", lower, upper));
                } else {
                    break;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Enter only Integer");
            }

        }
        return io;
    }

    public String getValidStringType () {
        String io = "";
        io = helper.getStringInput();
        while(true){
           // io = sc.nextLine();
            if(io.isEmpty()){
                System.out.println("Enter Non-empty Value");
            }
            else
                break;
        }
        return io;
    }

    public static int playToss() {
        return ThreadLocalRandom.current().nextInt(Constants.lowerTossBound,Constants.upperTossBound);
    }


    public static int getRandomRun() {
        int [] numbers= helper.getIntegerArray();
        int ix = ThreadLocalRandom.current().nextInt(0, 100);
        //int ix = (int)(Rand() * 100);
        int num = numbers[ix];
        return num;
     }

    public static int getRandomBowler() {
        return ThreadLocalRandom.current().nextInt(Constants.lowerBowlerIndex, Constants.upperBowlerIndex);
        //Random random = new Random();
        //return random.ints(5, 10).findFirst().getAsInt();
     }

    public static int getTotalWicketOut(Inning inning) {
         int wicketCount = 0;
         for (int i = 0; i <inning.getOverDetails().size() ; i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);

            for (int j = 0; j < currentOverDetails.getBallDetails().size()  ; j++) {
                BallDetails currentBallDetails = currentOverDetails.getBallDetails().get(j);
                if (currentBallDetails.getBallType() == BallType.WICKET) {
                    wicketCount ++;
                }
            }
        }
        return  wicketCount;
    }

    public static int getScoreOfInning(Inning inning) {

         int score = 0;
         for (int i = 0; i <inning.getOverDetails().size() ; i++) {

            OverDetails currentOverDetails = inning.getOverDetails().get(i);

            for (int j = 0; j < currentOverDetails.getBallDetails().size()  ; j++) {
                BallDetails currentBallDetails = currentOverDetails.getBallDetails().get(j);
                score += currentBallDetails.getScoreOnBall();
            }
        }
        return  score;
    }


    public static int getPlayerWiseScore(Player p, Inning inning) {

        int score = 0;
       // Player p = inning.getBattingTeam().getPlayers().get(player);
        for (int i = 0; i <inning.getOverDetails().size() ; i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);

            for (int j = 0; j < currentOverDetails.getBallDetails().size()  ; j++) {

                if(currentOverDetails.getBallDetails().get(j).getStrikerOnBall() == p ) {
                    score += currentOverDetails.getBallDetails().get(j).getScoreOnBall();

                }
            }
        }
        return  score;
    }

    public static int getTotalBallsPlayed (Player player, Inning inning) {
         int balls = 0;

        for (int i = 0; i < inning.getOverDetails().size(); i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);

            for (int j = 0; j < currentOverDetails.getBallDetails().size(); j++) {
                BallDetails currentBallDetails = currentOverDetails.getBallDetails().get(j);

                if(currentBallDetails.getStrikerOnBall() == player) {
                    balls ++;
                }

            }

        }
        return  balls;
    }

    public static int getWicketTakenByBowler (Player player, Inning inning) {
        int wicketTaken = 0;
        for (int i = 0; i < inning.getOverDetails().size(); i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);
            Player bowler = currentOverDetails.getBowler();
            if(bowler != player) continue;
            for (int j = 0; j < currentOverDetails.getBallDetails().size(); j++) {
                BallDetails currentBallDetails = currentOverDetails.getBallDetails().get(j);
                if(currentBallDetails.getBallType() == BallType.WICKET) {
                    wicketTaken ++;
                }

            }
        }
        return  wicketTaken;
     }



}
