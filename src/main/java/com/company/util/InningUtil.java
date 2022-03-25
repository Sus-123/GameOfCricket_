package com.company.util;

import com.company.constants.Constants;
import com.company.entity.matchEntity.*;

import java.util.concurrent.atomic.AtomicInteger;

public class InningUtil {

    public static int getScoreOfInning(Inning inning) {
        AtomicInteger score = new AtomicInteger();
        inning.getOverDetails().stream().forEach(over ->{over.getBallDetails().stream().forEach(ball ->{score.addAndGet(ball.getScoreOnBall());});});
        return score.get();
    }

    public static int getBallsPlayedOfInning(Inning inning) {

        AtomicInteger ballsPlayed = new AtomicInteger();
        inning.getOverDetails().stream().forEach(over ->{over.getBallDetails().stream().forEach(ball ->{ballsPlayed.incrementAndGet();});});
        return ballsPlayed.get();
    }

    public static int getTotalWicketOut(Inning inning) {
        AtomicInteger wicketCount = new AtomicInteger();
        inning.getOverDetails().stream().forEach(over -> {wicketCount.addAndGet( (int) over.getBallDetails() .stream().filter(ball -> ball.getBallType() == BallType.WICKET).count());});
        return wicketCount.get();
    }

    public static boolean checkMatchEnd (Inning inning) {
        boolean allOut = false;
        if(Constants.totalPlayerInTeam-1 == getTotalWicketOut(inning) )
            allOut = true;

        if (allOut || (inning.isChaser() && (inning.getScoreToChase() < InningUtil.getScoreOfInning(inning) )))
            return  true;
        return false;
    }


}



