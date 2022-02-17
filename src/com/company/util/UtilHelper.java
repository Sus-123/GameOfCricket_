package com.company.util;
import java.util.Scanner;

public class UtilHelper {

    Scanner sc = new Scanner(System.in);

    public int getIntegerInput() {
        int io;
        io = Integer.parseInt(sc.nextLine());
        return io;
    }

    public String getStringInput () {
        String io = "";
        io = sc.nextLine();
        return io;
    }

}
