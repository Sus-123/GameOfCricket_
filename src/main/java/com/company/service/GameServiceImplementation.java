package com.company.service;

import com.company.constants.Constants;
import com.company.entity.matchEntity.*;
import com.company.repozitory.BallDetailsRepository;
import com.company.repozitory.OverDetailsRepository;
import com.company.util.InningUtil;
import com.company.util.PlayerUtil;
import com.company.util.GameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameServiceImplementation {

    @Autowired
    private OverDetailsRepository overDetailsRepository;

    @Autowired
    private BallDetailsRepository ballDetailsRepository;


    /**
     * playInning  will play a particular inning
     * @param inning
     * @param inningId
     */

    public void playInning (Inning inning, int inningId)  {

        System.out.println("Team " + inning.getBattingTeam().getName() + " Started the match ");

        for (int i = Constants.ONE; i <= inning.getNumOfOver(); i++) {

            int currentBowlerIndex = GameUtil.getRandomBowler();
            if (InningUtil.checkMatchEnd(inning)) break;
            playOver(inning, i, inningId, currentBowlerIndex);
            inning.strike.changeStrikeOnOver();
        }
    }



    /**
     * playOver  will be called for every over
     * @param inning : inning obj
     * @param over : which over to play
     * @param inningId
     * @param currentBowlerIndex
     */

    public void playOver (Inning inning, int over, int inningId, int currentBowlerIndex) {
        OverDetails overDetails = new OverDetails();
        inning.setOverDetails(overDetails);

        String bowlerName = inning.getBowlingTeam().getPlayers().get(currentBowlerIndex).getPlayerName();
        System.out.println("Playing Over: " + over + " Bowler is: " + bowlerName);

        int overDetailsId = overDetailsRepository.insertOverDetails(inningId, bowlerName, inning.getBowlingTeam().getName());
        inning.getOverDetails().get(over-1).setBowler(inning.getBowlingTeam().getPlayers().get(currentBowlerIndex));
        for (int j = Constants.ONE; j <= Constants.totalBallInOver; j++) {

            if (InningUtil.checkMatchEnd(inning)) break;

            BallDetails ballDetails = handleBall( inning, j);

            inning.getOverDetails().get(over-1).getBallDetails().add(ballDetails);
            ballDetailsRepository.insertBallDetails(ballDetails, overDetailsId, inning.getBattingTeam().getName(), inningId);

        }
    }




    /**
     * handleBall  will be called to handle every individual ball
     * @param inning : inning obj
     * @param currentBall : which ball to play
     */

    BallDetails handleBall( Inning inning, int currentBall) {
        BallDetails ballDetails = new BallDetails();
        int runs = GameUtil.getRandomRun() ;

        ballDetails.setStrikerOnBall(inning.getBattingTeam().getPlayers().get(inning.strike.getcurrentStriker()));
        if (runs < 7) {
            System.out.println(currentBall + " : " + runs + " Runs by " + ballDetails.getStrikerOnBall().getPlayerName());
            inning.strike.changeStrikeOnRun(runs);

            ballDetails.setScoreOnBall(runs);
            ballDetails.setBallType(BallType.RUN);
            return  ballDetails;
        }

        ballDetails.setScoreOnBall(0);
        ballDetails.setBallType(BallType.WICKET);
        int outPlayer = inning.strike.changeStrikeOnWicket();
        Player p = inning.getBattingTeam().getPlayers().get(outPlayer);
        System.out.println("Player " + inning.getBattingTeam().getPlayers().get(outPlayer).getPlayerName() + " Out with " + PlayerUtil.getPlayerWiseScore(p,inning) );

        return  ballDetails ;
    }


}

















