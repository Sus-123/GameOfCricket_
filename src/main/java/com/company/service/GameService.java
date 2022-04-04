package com.company.service;

import com.company.constants.Constants;
import com.company.entity.matchEntity.Inning;
import com.company.entity.matchEntity.Strike;
import com.company.entity.matchEntity.Team;
import com.company.repozitory.InningRepository;
import com.company.repozitory.MatchRepository;
import com.company.repozitory.TeamRepository;
import com.company.util.InningUtil;
import com.company.util.GameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameService {

    @Autowired
    private GameServiceImplementation gameServiceHelper;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private InningRepository inningRepository;
    @Autowired
    private MatchRepository matchRepository;



    /**
     * instantiateNewGame  will initialise a new match, between two team
     * @param team1Name
     * @param team2Name
     * @param numOfOver : total num of over to be played
     */

    public void initializeNewGame(String matchName, String team1Name, String team2Name, int numOfOver)  {
        Inning inning1 = null;
        Inning inning2 = null;


        Team team1 = new Team(team1Name);
        Team team2 = new Team(team2Name);

        int team1Id = teamRepository.insertTeam(team1);
        int team2Id = teamRepository.insertTeam(team2);


        if(GameUtil.playToss() == Constants.ZERO) {

            int inning1Id = inningRepository.insertInning(team1Id, team2Id, numOfOver);
            int inning2Id = inningRepository.insertInning(team2Id, team1Id, numOfOver);
            matchRepository.insertMatch(inning1Id, inning2Id, matchName);


            Strike strike1 = new Strike();
            inning1 = new Inning(team1, team2, false, 0, numOfOver, strike1);
            gameServiceHelper.playInning(inning1, inning1Id);
            System.out.println(team1.getTeamName() + " ended game with " + InningUtil.getScoreOfInning(inning1));

            System.out.println("--------Second Inning -------");


            Strike strike2 = new Strike();
            inning2 = new Inning(team2, team1, true, InningUtil.getScoreOfInning(inning1), numOfOver, strike2);
            gameServiceHelper.playInning(inning2, inning2Id);
            System.out.println(team2.getTeamName() + " ended game with " + InningUtil.getScoreOfInning(inning2));

        }
        else {
            int inning1Id = inningRepository.insertInning(team2Id, team1Id, numOfOver);
            int inning2Id = inningRepository.insertInning(team1Id, team2Id, numOfOver);
            matchRepository.insertMatch(inning1Id, inning2Id, matchName);

            Strike strike1 = new Strike();
            inning1 = new Inning(team2, team1, false, 0, numOfOver, strike1);
            gameServiceHelper.playInning(inning1, inning1Id);
            System.out.println(team2.getTeamName() + " ended game with " + InningUtil.getScoreOfInning(inning1));

            System.out.println("--------Second Inning -------");

            Strike strike2 = new Strike();
            inning2 = new Inning(team1, team2, true, InningUtil.getScoreOfInning(inning1), numOfOver, strike2);
            gameServiceHelper.playInning(inning2, inning2Id);
            System.out.println(team1.getTeamName() + " ended game with " + InningUtil.getScoreOfInning(inning2));
        }
    }

}
