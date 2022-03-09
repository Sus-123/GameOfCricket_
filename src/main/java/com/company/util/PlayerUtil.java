package com.company.util;


import com.company.entity.matchEntity.*;

public class PlayerUtil {


    public static int getPlayerWiseScore(Player p, Inning inning) {

        int score = 0;
        for (int i = 0; i <inning.getOverDetails().size() ; i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);

            for (int j = 0; j < currentOverDetails.getBallDetails().size()  ; j++) {
                if(currentOverDetails.getBallDetails().get(j).getStrikerOnBall().getPlayerName().equals(p.getPlayerName())  ) {
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

                if(currentBallDetails.getStrikerOnBall().getPlayerName().equals(player.getPlayerName())) {
                    balls ++;
                }
            }
        }

        return  balls;
    }



    public static int getCenturies(Player player, Inning inning) {
        return  getPlayerWiseScore(player,inning)/100;
    }

    public static int getSixes(Player player, Inning inning) {

        int sixes = 0;
        for (int i = 0; i <inning.getOverDetails().size() ; i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);

            for (int j = 0; j < currentOverDetails.getBallDetails().size()  ; j++) {
                if(currentOverDetails.getBallDetails().get(j).getStrikerOnBall().getPlayerName().equals(player.getPlayerName())  ) {
                    int score = currentOverDetails.getBallDetails().get(j).getScoreOnBall();
                    if(score == 6) sixes++;
                }
            }
        }

        return  sixes;

    }

    public static int getFours(Player player, Inning inning) {

        int fours = 0;
        for (int i = 0; i <inning.getOverDetails().size() ; i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);

            for (int j = 0; j < currentOverDetails.getBallDetails().size()  ; j++) {
                if(currentOverDetails.getBallDetails().get(j).getStrikerOnBall().getPlayerName().equals(player.getPlayerName())  ) {
                    int score = currentOverDetails.getBallDetails().get(j).getScoreOnBall();
                    if(score == 4) fours++;
                }
            }
        }

        return  fours;

    }

    public static int getWicketTakenByBowler (Player player, Inning inning) {
        int wicketTaken = 0;
        for (int i = 0; i < inning.getOverDetails().size(); i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);
            Player bowler = currentOverDetails.getBowler();

            if(bowler.getPlayerName().equals(player.getPlayerName())) {
                for (int j = 0; j < currentOverDetails.getBallDetails().size(); j++) {
                    BallDetails currentBallDetails = currentOverDetails.getBallDetails().get(j);
                    if (currentBallDetails.getBallType().equals(BallType.WICKET)) {
                        wicketTaken++;
                    }

                }
            }

        }
        return  wicketTaken;
    }

    public static int getBallsBowledByBowler(Player player, Inning inning) {
        int ballsBowled = 0;
        for (int i = 0; i < inning.getOverDetails().size(); i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);
            Player bowler = currentOverDetails.getBowler();
            if(bowler.getPlayerName().equals(player.getPlayerName()))
                ballsBowled += currentOverDetails.getBallDetails().size();
        }
        return  ballsBowled;
    }







}
