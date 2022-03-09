package com.company.service;
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


@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    @Autowired
    InningRepository inningRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    GameService gameService;


    public MatchStats playMatch (int numOfOvers, String teamAName, String teamBName, String matchName) {
        try {
            gameService.initializeNewGame(matchName, teamAName, teamBName, numOfOvers );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return initialiseMatch(matchName);
    }


    public MatchStats initialiseMatch(String matchName) {
        try {
            int id = matchRepository.getMatchIdByName(matchName);
            ArrayList<Integer> inningsIds = matchRepository.getInningId(id);

            Inning inning1 = inningRepository.createInning(inningsIds.get(0));
            Inning inning2 = inningRepository.createInning(inningsIds.get(1));

            MatchStats matchStats = initialiseMatchStats(inning1, inning2, id);
            return matchStats ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public MatchStats initialiseMatchStats(Inning inning1, Inning inning2, int matchId) {
        try {
            int teamAid = teamRepository.getTeamIdFromTeamName(inning1.getBattingTeam().getName());
            int teamBid = teamRepository.getTeamIdFromTeamName(inning2.getBattingTeam().getName());
            int overs = inning2.getNumOfOver();

            TeamStats teamAStats = new TeamStats(teamAid, inning1.getBattingTeam().getName(), InningUtil.getScoreOfInning(inning1), InningUtil.getWicketFallen(inning1), InningUtil.getBallsPlayedOfInning(inning1));

            TeamStats teamBStats = new TeamStats(teamBid, inning2.getBattingTeam().getName(), InningUtil.getScoreOfInning(inning2), InningUtil.getWicketFallen(inning2), InningUtil.getBallsPlayedOfInning(inning2));

            MatchStats matchStats = new MatchStats(matchId, teamAStats, teamBStats, overs, Util.getWinningTeam(inning1, inning2));

            return matchStats;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


}
