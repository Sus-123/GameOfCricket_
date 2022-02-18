package com.company.util;
import com.company.Constants;
import com.company.entity.BallType;
import com.company.entity.Inning;
import com.company.entity.OverDetails;
import com.company.entity.Player;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Util {
     Scanner sc = new Scanner(System.in);
     UtilHelper helper = new UtilHelper();
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
        return ThreadLocalRandom.current().nextInt(Constants.lowerRunBound, Constants.upperRunBound);
    }

    public static int getRandomBowler() {
        Random random = new Random();
        return random.ints(5, 10).findFirst().getAsInt();
    }

    public static int getTotalWicketOut(Inning inning) {
         int wicketCount = 0;
         for (int i = 0; i <inning.getOverDetails().size() ; i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);

            for (int j = 0; j < currentOverDetails.getBallDetails().size()  ; j++) {
                if (currentOverDetails.getBallDetails().get(j).getBallType() == BallType.WICKET) {
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
                score += currentOverDetails.getBallDetails().get(j).getScoreOnBall();
            }
        }
        return  score;
    }

    public static int getPlayerWiseScore(int player, Inning inning) {
        int score = 0;
        Player p = inning.getBattingTeam().getPlayers().get(player);
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


}
