package com.company;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class game {
    private Team team1;
    private Team team2;
    private int totalAvailableBalls;
    private  int overs;


    game () {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Total over to be played : ");
        overs = sc.nextInt();
        totalAvailableBalls = overs*6;
        sc.nextLine();
        team1 = new Team(totalAvailableBalls);
        team2 = new Team(totalAvailableBalls);
    }

    int playToss() {
        return ThreadLocalRandom.current().nextInt(0,2);
    }

    void playInningFirst (Team team) {
        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < overs; i++) {
            System.out.println("Over: "+(i+1));
            for (int j = 0; j < 6; j++) {

                sc.nextLine();
                int runs = ThreadLocalRandom.current().nextInt(0,7);
                team.increaseTotalBallsPlayed();

                Player p = team.getCurrentPlayerData();
                //wicket out
                if(runs == 5) {
                    System.out.println(String.format("Player %s from  %s team got bold out", p.getName(),team));
                    System.out.println(String.format("With Total run: %d , On Total balls %d: ", p.getScore(),p.getBallsPlayed()));
                    team.wicketOut();
                    int totalWicket = team.getTotalWicket();
                    if(totalWicket >= 10) {
                        System.out.println(String.format("Team, %s got complete bold out! ",  team.getName()));
                        break ;
                    }
                } else {
                    System.out.println(String.format("Wuh! Player %s scored %d: ", p.getName(),runs));
                    team.increaseTeamScore(runs);
                }

            }
            int totalWicket = team.getTotalWicket();
            if(totalWicket >= 10) {
                System.out.println(String.format("Team, %s got complete bold out! ",  team.getName()));
                break ;
            }

        }

    }

    void startGame () {
        int toss = playToss();

        if(toss == 0) {
            playInningFirst(team1);
        }

    }

}
