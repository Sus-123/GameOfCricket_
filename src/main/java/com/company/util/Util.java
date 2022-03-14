package com.company.util;

import com.company.constants.Constants;
import com.company.entity.matchEntity.Inning;

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



    public static int playToss() {
        return ThreadLocalRandom.current().nextInt(Constants.lowerTossBound,Constants.upperTossBound);
    }


    public static int getRandomRun() {
        int [] numbers= helper.getIntegerArray();
        int ix = ThreadLocalRandom.current().nextInt(0, 100);
        int num = numbers[ix];
        return num;
     }

    public static int getRandomBowler() {
        return ThreadLocalRandom.current().nextInt(Constants.lowerBowlerIndex, Constants.upperBowlerIndex);
     }


    public static String getWinningTeam(Inning inning1, Inning inning2) {

         if(InningUtil.getScoreOfInning(inning1) > InningUtil.getScoreOfInning(inning2)) {
             return  inning1.getBattingTeam().getName();
         }

         return inning2.getBattingTeam().getName();

    }











}
