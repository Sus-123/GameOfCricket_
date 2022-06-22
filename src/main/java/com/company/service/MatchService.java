package com.company.service;

import com.company.Exception.GameExceptions;
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
import com.company.util.GameUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    /**
     * playMatch  will play a new match, between two team with given num of overs, team names and match name
     * @param numOfOvers
     * @param team1Name
     * @param team2Name
     * @param matchName
     */
    public MatchStats playMatch (int numOfOvers, String team1Name, String team2Name, String matchName)  {

        if (matchName.isEmpty() || team1Name.isEmpty() || team2Name.isEmpty()) {
            throw new GameExceptions("Match Name or Team Names can not be empty! ", HttpStatus.NOT_ACCEPTABLE);
        }
        if (team1Name.equals(team2Name)) {
            throw new GameExceptions("Both teams can not be same! ", HttpStatus.NOT_ACCEPTABLE);
        }
        if (matchRepository.checkIfMatchExist(matchName)) {
            throw new GameExceptions("Match with name " + matchName + " already exist. Please play new match", HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            gameService.initializeNewGame(matchName, team1Name, team2Name, numOfOvers);
        } catch (Exception e) {
            throw new GameExceptions("Game can not be played, due to internal errors in game service!", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return getMatch(matchName);
    }



    /**
     * get Match  will fetch a particular match, using matchName only
     * @param matchName
     */
    public MatchStats getMatch(String matchName) {

        if(!matchRepository.checkIfMatchExist(matchName)) {
            throw new GameExceptions("Match with name "+ matchName + " does not exist.", HttpStatus.NOT_FOUND);
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



    /**
     * initialiseMatchStats  will initialise every match details in the response object, which get match request
     * @param inning1 : First played inning in thatv particular match
     * @param inning2
     */

    public MatchStats initialiseMatchStats(Inning inning1, Inning inning2, String matchName, ArrayList<Team> teams) {

            MatchStats matchStats = null;

            ArrayList<PlayerStatsInSingleMatch> teamAPlayersStats = initialisePlayerStats(inning1, inning2, teams.get(0));
            ArrayList<PlayerStatsInSingleMatch> teamBPlayersStats = initialisePlayerStats(inning1, inning2, teams.get(1));

            int overs = inning2.getNumOfOver();
            TeamStats teamAStats = new TeamStats(inning1.getBattingTeam().getTeamName(), InningUtil.getScoreOfInning(inning1), InningUtil.getTotalWicketOut(inning1), InningUtil.getBallsPlayedOfInning(inning1), teamAPlayersStats);
            TeamStats teamBStats = new TeamStats(inning2.getBattingTeam().getTeamName(), InningUtil.getScoreOfInning(inning2), InningUtil.getTotalWicketOut(inning2), InningUtil.getBallsPlayedOfInning(inning2), teamBPlayersStats);

            matchStats = new MatchStats(matchName, teamAStats, teamBStats, overs, GameUtil.getWinningTeam(inning1, inning2));
            return matchStats;
    }



    /**
     * initialisePlayerStats  will initialise every player of a particular team , which is used by get match request
     * @param inning1 : First played inning in that particular match
     * @param inning2
     * @param team : Team for which data needed
     */

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
