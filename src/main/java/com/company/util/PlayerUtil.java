package com.company.util;
import com.company.entity.matchEntity.*;

import java.util.concurrent.atomic.AtomicInteger;


public class PlayerUtil {

    public static int getPlayerWiseScore(Player p, Inning inning) {
        AtomicInteger score = new AtomicInteger();
        inning.getOverDetails().stream().forEach(over -> {
            over.getBallDetails().stream()
                    .filter(ball->ball.getStrikerOnBall().getPlayerName().equals(p.getPlayerName()))
                    .forEach(ball -> {
                score.addAndGet(ball.getScoreOnBall());
            });
        });

        return score.get();
    }

    public static int getTotalBallsPlayed (Player player, Inning inning) {
        AtomicInteger totalBalls = new AtomicInteger();
        inning.getOverDetails().stream().forEach(over -> {
            over.getBallDetails().stream()
                    .filter(ball->ball.getStrikerOnBall().getPlayerName().equals(player.getPlayerName()))
                    .forEach(ball -> {
                totalBalls.incrementAndGet();
            });
        });
        return totalBalls.get();
    }

    public static int getSixes(Player player, Inning inning) {
        AtomicInteger sixes = new AtomicInteger();
        inning.getOverDetails().stream()
                .forEach(over -> {over
                        .getBallDetails().stream()
                        .filter( ball->ball.getStrikerOnBall().getPlayerName().equals(player.getPlayerName()) )
                        .filter(ball -> ball.getScoreOnBall() == 6)
                        .forEach(ball ->{
                            sixes.incrementAndGet();
                        });

            });

        return sixes.get();

    }

    public static int getFours(Player player, Inning inning) {
        AtomicInteger fours = new AtomicInteger();
        inning.getOverDetails().stream()
                .forEach(over -> {over
                        .getBallDetails().stream()
                        .filter( ball->ball.getStrikerOnBall().getPlayerName().equals(player.getPlayerName()) )
                        .filter(ball -> ball.getScoreOnBall() == 4)
                        .forEach(ball ->{
                            fours.incrementAndGet();
                        });

                });
        return fours.get();
    }

    public static int getWicketTakenByBowler (Player player, Inning inning) {

        AtomicInteger wicketTaken = new AtomicInteger();
        inning.getOverDetails().stream()
                .filter(over->over.getBowler().getPlayerName().equals(player.getPlayerName()))
                .forEach(over-> {over
                        .getBallDetails().stream()
                        .filter(ball-> ball.getBallType().equals(BallType.WICKET))
                        .forEach(ball->{
                            wicketTaken.incrementAndGet();
                        });
            });
        return wicketTaken.get();
    }

    public static int getBallsBowledByBowler(Player player, Inning inning) {

        AtomicInteger ballsBowled = new AtomicInteger();
        inning.getOverDetails().stream()
                .filter(over->over.getBowler().getPlayerName().equals(player.getPlayerName()))
                .forEach(over-> {
                    ballsBowled.addAndGet(over.getBallDetails().size());
                });
        return ballsBowled.get();
    }


    public static int getCenturies(Player player, Inning inning) {
        return  getPlayerWiseScore(player,inning)/100;
    }

}
