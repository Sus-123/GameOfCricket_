package com.company.util;
import com.company.Constants;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class Util {
    Scanner sc = new Scanner(System.in);
    UtilHelper helper = new UtilHelper();

    /**
     * getIntegerInput : take user input from Helper class and validates it
     * @param lower : lower bound of overs to be played
     * @param upper : upper bound of overs to be played.
     */
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

    /**
     * getValidStringType : takes valid user input from Helper class and validates it
     */

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
//        String io;
//        while (true) {
//            try {
//                io = sc.nextLine();
//                if ( io.isEmpty()) {
//                    System.out.println("Enter non-empty String");
//                }
//                else
//                    break;
//            } catch (InvalidStreamException e) {
//                System.out.println("Enter valid String");
//            }
//        }
//
//        return io;
    }

    /**
     * getValidStringType : takes type of each player and check if its Batsman or baller
     */
    public String getPlayerType() {
        String io = "";
        while(true){
            io = sc.nextLine().toUpperCase();
            if(io.equals("BATSMAN") || io.equals("BOWLER")) {
                return io;
            }
            else
                System.out.println("Enter Valid Type, BATSMAN or BOWLER only");
        }

    }

    /**
     * playToss : play toss to decide which team will go first
     */

    public static int playToss() {
        return ThreadLocalRandom.current().nextInt(Constants.lowerTossBound,Constants.upperTossBound);
    }

    /**
     * getRandomRun : generates random run for the player
     */
    public static int getRandomRun() {
        return ThreadLocalRandom.current().nextInt(Constants.lowerRunBound, Constants.upperRunBound);
    }

}
