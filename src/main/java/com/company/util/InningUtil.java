package com.company.util;

import com.company.constants.Constants;
import com.company.entity.matchEntity.*;

public class InningUtil {

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


    public static int getBallsPlayedOfInning(Inning inning) {

        int ballsPlayed = 0;
        for (int i = 0; i < inning.getOverDetails().size(); i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);
            for (int j = 0; j < currentOverDetails.getBallDetails().size(); j++) {
                ballsPlayed++;
            }
        }

        return  ballsPlayed;

    }

    public static int getWicketFallen(Inning inning) {

        int wicketFallen = 0;
        for (int i = 0; i < inning.getOverDetails().size(); i++) {
            OverDetails currentOverDetails = inning.getOverDetails().get(i);
            Player bowler = currentOverDetails.getBowler();
            for (int j = 0; j < currentOverDetails.getBallDetails().size(); j++) {
                BallDetails currentBallDetails = currentOverDetails.getBallDetails().get(j);

                if (currentBallDetails.getBallType().equals(BallType.WICKET)) {
                    wicketFallen++;
                }
            }
        }
        return  wicketFallen;

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

    public static boolean checkMatchEnd (Inning inning) {
        boolean allOut = false;
        if(Constants.totalPlayerInTeam-1 == getTotalWicketOut(inning) ) {
            allOut = true;
        }
        if (allOut || (inning.isChaser() && (inning.getScoreToChase() < InningUtil.getScoreOfInning(inning) ))) {
            return  true;
        }

        return false;
    }




}
