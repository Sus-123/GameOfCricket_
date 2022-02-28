package com.company.service;
import com.company.Constants;
import com.company.database.DbOperations;
import com.company.entity.*;
import com.company.scoreboard.ScoreBoard;
import com.company.util.Util;

import java.sql.SQLException;

public class GameService {

    GameServiceHelper gameServiceHelper = new GameServiceHelper();
    Inning inning1;
    Inning inning2;

    /**
     * instantiateNewGame  will initialise a new match, between two team
     * @param team1
     * @param team2
     * @param numOfOver : total numb of over to be played
     */

    public void initializeNewGame(Team team1, Team team2, int numOfOver) throws SQLException, ClassNotFoundException {
        int team1Id = DbOperations.insertTeamInDb(team1);
        int team2Id = DbOperations.insertTeamInDb(team2);
        int matchId = DbOperations.insertMatchInDb(team1Id,team2Id,numOfOver);

        if(Util.playToss() == Constants.ZERO) {
            int inning1Id = DbOperations.insertInningInDb(matchId);
            int inning2Id = DbOperations.insertInningInDb(matchId);

            Strike strike1 = new Strike();
            inning1 = new Inning(team1, team2, false, 0, numOfOver, strike1);
            gameServiceHelper.playInning(inning1, inning1Id);
            System.out.println(team1.getName() + " ended game with " + Util.getScoreOfInning(inning1));


            Strike strike2 = new Strike();
            inning2 = new Inning(team2, team1, true, Util.getScoreOfInning(inning1), numOfOver, strike2);
            gameServiceHelper.playInning(inning2, inning2Id);
            System.out.println(team2.getName() + " ended game with " + Util.getScoreOfInning(inning2));

        }
        else {

            int inning1Id = DbOperations.insertInningInDb(matchId);
            int inning2Id = DbOperations.insertInningInDb(matchId);


            Strike strike1 = new Strike();
            inning1 = new Inning(team2, team1, false, 0, numOfOver, strike1);
            gameServiceHelper.playInning(inning1, inning1Id);
            System.out.println(team2.getName() + " ended game with " + Util.getScoreOfInning(inning1));

            Strike strike2 = new Strike();
            inning2 = new Inning(team1, team2, true, Util.getScoreOfInning(inning1), numOfOver, strike2);
            gameServiceHelper.playInning(inning2, inning2Id);
            System.out.println(team1.getName() + " ended game with " + Util.getScoreOfInning(inning2));
        }

    }

    public void  showFinalScoreBoard () {

        ScoreBoard.showMatchResult(inning1, inning2);

    }


}
