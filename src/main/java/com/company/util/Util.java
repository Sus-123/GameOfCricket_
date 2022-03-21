package com.company.util;

import com.company.constants.Constants;
import com.company.entity.matchEntity.Inning;

import java.util.concurrent.ThreadLocalRandom;


public class Util {

     static UtilHelper helper = new UtilHelper();



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
