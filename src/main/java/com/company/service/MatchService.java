package com.company.service;

import com.company.Exception.InvalidInputException;
import com.company.entity.matchEntity.Inning;
import com.company.entity.matchEntity.Player;
import com.company.entity.matchEntity.Team;
import com.company.response.MatchStats;
import com.company.response.PlayerStatsInSingleMatch;
import com.company.response.TeamStats;
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
    private MatchRepository matchRepository;
    @Autowired
    private InningRepository inningRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService playerService;


    public MatchStats playMatch (int numOfOvers, String team1Name, String team2Name, String matchName)  {

        if (matchName.isEmpty() || team1Name.isEmpty() || team2Name.isEmpty()) {
            throw new InvalidInputException("Match Name or Team Names can not be empty! ");
        }
        if (team1Name.equals(team2Name)) {
            throw new InvalidInputException("Both teams can not be same! ");
        }
        if (matchRepository.checkIfMatchExist(matchName)) {
            throw new InvalidInputException("Match with name " + matchName + " already exist. Please play new match");
        }

        try {
            gameService.initializeNewGame(matchName, team1Name, team2Name, numOfOvers);
        } catch (Exception e) {
            throw new IllegalStateException("Game can not be played, due to internal errors in game service!");
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
        ArrayList<Team> teams = inningRepository.getTeams(inningsIds.get(0));

        Inning inning1 = inningRepository.getInning(inningsIds.get(0));
        Inning inning2 = inningRepository.getInning(inningsIds.get(1));

        matchStats = initialiseMatchStats(inning1, inning2, matchName, teams);
        return matchStats ;
    }


    public MatchStats initialiseMatchStats(Inning inning1, Inning inning2, String matchName, ArrayList<Team> teams) {

            MatchStats matchStats = null;

            ArrayList<PlayerStatsInSingleMatch> teamAPlayersStats = initialisePlayerStats(inning1, inning2, teams.get(0));
            ArrayList<PlayerStatsInSingleMatch> teamBPlayersStats = initialisePlayerStats(inning1, inning2, teams.get(1));

            int overs = inning2.getNumOfOver();
            TeamStats teamAStats = new TeamStats(inning1.getBattingTeam().getName(), InningUtil.getScoreOfInning(inning1), InningUtil.getWicketFallen(inning1), InningUtil.getBallsPlayedOfInning(inning1), teamAPlayersStats);
            TeamStats teamBStats = new TeamStats(inning2.getBattingTeam().getName(), InningUtil.getScoreOfInning(inning2), InningUtil.getWicketFallen(inning2), InningUtil.getBallsPlayedOfInning(inning2), teamBPlayersStats);

            matchStats = new MatchStats(matchName, teamAStats, teamBStats, overs, Util.getWinningTeam(inning1, inning2));
            return matchStats;
    }

    public ArrayList<PlayerStatsInSingleMatch> initialisePlayerStats (Inning inning1, Inning inning2, Team team) {

        ArrayList<PlayerStatsInSingleMatch> playersStats = new ArrayList<>();

        for (int i = 0; i < team.getPlayers().size(); i++) {
            Player player = team.getPlayers().get(i);
            PlayerStatsInSingleMatch ps = playerService.getPlayerStats(inning1, inning2, player);
            playersStats.add(ps);
        }

        return playersStats;

    }


}
