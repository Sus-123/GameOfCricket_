package com.company.util;


public class GameUtilHelper {

    private static int[] numbers = new int[100];
    public GameUtilHelper() {
        //we can set probability of different run according to our choice.
        //like here probability of 5 is zero.
        for (int i = 0; i < 10; ++i)
            numbers[i] = 0;
        for (int i = 10; i < 30; ++i)
            numbers[i] = 1;
        for (int i = 30; i < 50; ++i)
            numbers[i] = 2;
        for (int i = 50; i < 60; ++i)
            numbers[i] = 3;
        for (int i = 60; i < 75; ++i)
            numbers[i] = 4;
        for (int i = 75; i < 90; ++i)
            numbers[i] = 6;
        for (int i = 90; i < 100; ++i)
            numbers[i] = 7;
    }

    public int [] getIntegerArray () {
        return this.numbers;
    }



}
