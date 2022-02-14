package com.company;
import com.sun.nio.sctp.InvalidStreamException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


public class GameUtil {
    Scanner sc = new Scanner(System.in);

    public  int getIntegerInput(int lower, int upper) {
        int io;
        while(true) {
            //io = sc.nextInt();
            try {
                io = Integer.parseInt(sc.nextLine());
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
        String input = "";
        while(true){
            input = sc.nextLine();
            if(input.isEmpty()){
                System.out.println("Enter Non-empty Value");
            }
            else
                break;
        }
        return input;
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

    public int playToss() {
        return ThreadLocalRandom.current().nextInt(0,2);
    }

    public  int getRandomRun() {
        return ThreadLocalRandom.current().nextInt(0, 8);
    }

}
