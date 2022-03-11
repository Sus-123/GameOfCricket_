package com.company.service;
import com.company.Exception.ErrorDetails;
import com.company.Exception.InvalidInputException;
import com.company.entity.matchEntity.Inning;
import com.company.entity.responseEntity.MatchStats;
import com.company.entity.responseEntity.TeamStats;
import com.company.repozitory.InningRepository;
import com.company.repozitory.MatchRepository;
import com.company.repozitory.TeamRepository;
import com.company.util.InningUtil;
import com.company.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;


@Service
public class MatchService {

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private InningRepository inningRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GameService gameService;


    public MatchStats playMatch (int numOfOvers, String team1Name, String team2Name, String matchName)  {

        if (matchName.isEmpty() || team1Name.isEmpty() || team2Name.isEmpty()) {
            System.out.println("name empty check!");
            throw new InvalidInputException("Match Name or Team Names can not be empty! ");
        }
        if (team1Name.equals(team2Name)) {
            System.out.println("team name error check");
            throw new IllegalStateException("Both teams can not be same! ");
                //throw new ErrorDetails(new Date(), "same name", "details error");
        }
        if (matchRepository.checkIfMatchExist(matchName)) {
            System.out.println("Match existance check!");
            throw new IllegalStateException("Match with name " + matchName + " already exist. Please play new match");
        }

        try {
            gameService.initializeNewGame(matchName, team1Name, team2Name, numOfOvers);
        } catch (Exception e) {
            throw new ErrorDetails(new Date(), "Game can not be played", e.getMessage());
        }

        return getMatch(matchName);
    }


    public MatchStats getMatch(String matchName) {

        if(!matchRepository.checkIfMatchExist(matchName)) {
            throw new IllegalStateException("Match with name "+ matchName + " does not exist.");
        }


        MatchStats matchStats = null;
        int id = matchRepository.getMatchIdByName(matchName);
        ArrayList<Integer> inningsIds = matchRepository.getInningId(id);

        Inning inning1 = inningRepository.getInning(inningsIds.get(0));
        Inning inning2 = inningRepository.getInning(inningsIds.get(1));

        matchStats = initialiseMatchStats(inning1, inning2, matchName);
        return matchStats ;
    }



    public MatchStats initialiseMatchStats(Inning inning1, Inning inning2, String matchName) {
            MatchStats matchStats = null;

            int teamAid = teamRepository.getTeamIdFromTeamName(inning1.getBattingTeam().getName());
            int teamBid = teamRepository.getTeamIdFromTeamName(inning2.getBattingTeam().getName());
            int overs = inning2.getNumOfOver();

            TeamStats teamAStats = new TeamStats(inning1.getBattingTeam().getName(), InningUtil.getScoreOfInning(inning1), InningUtil.getWicketFallen(inning1), InningUtil.getBallsPlayedOfInning(inning1));
            TeamStats teamBStats = new TeamStats(inning2.getBattingTeam().getName(), InningUtil.getScoreOfInning(inning2), InningUtil.getWicketFallen(inning2), InningUtil.getBallsPlayedOfInning(inning2));

            matchStats = new MatchStats(matchName, teamAStats, teamBStats, overs, Util.getWinningTeam(inning1, inning2));
            return matchStats;
    }


}
