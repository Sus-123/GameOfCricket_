package com.company.service;

import com.company.Exception.GameExceptions;
import com.company.entity.matchEntity.Inning;
import com.company.entity.matchEntity.Player;
import com.company.response.BattingStatsOfPlayer;
import com.company.response.BowlingStatsOfPlayer;
import com.company.response.PlayerStatsInSingleMatch;
import com.company.repozitory.InningRepository;
import com.company.repozitory.MatchRepository;
import com.company.repozitory.PlayersRepository;
import com.company.repozitory.TeamRepository;
import com.company.util.PlayerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class PlayerService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private PlayersRepository playersRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private InningRepository inningRepository;



    /**
     * initialisePlayerStats  will initialise every player of a particular team , which is used by get match request
     * @param  matchName : match in which player details needed
     * @param teamName: team from which player scores needed
     * @param playerName : name of player for which score card needed
     */

    public PlayerStatsInSingleMatch initialisePlayerStats(String matchName, String teamName, String playerName) {

        if(!matchRepository.checkIfMatchExist(matchName)) {
            throw new GameExceptions("Match with name "+ matchName + " does not exist.", HttpStatus.NOT_FOUND);
        }
        if(!teamRepository.checkIfTeamExist(teamName)) {
            throw new GameExceptions("Team with name "+ teamName + " does not exist.", HttpStatus.NOT_FOUND);
        }
        if(!teamRepository.checkIfPlayerExistInTeam(teamName, playerName)) {
            throw new GameExceptions("Player with name "+ playerName + " in Team " + teamName + " does not exist.", HttpStatus.NOT_FOUND);
        }

        int matchId = matchRepository.getMatchIdByName(matchName);
        int playerId = teamRepository.getPlayerId(playerName, teamName);

        ArrayList<Integer> inningsIds = matchRepository.getInningId(matchId);

        Inning inning1 = inningRepository.getInning(inningsIds.get(0));
        Inning inning2 = inningRepository.getInning(inningsIds.get(1));
        Player player = playersRepository.getPlayer(playerId);

        return getPlayerStats(inning1, inning2, player);


    }

    /**
     * getPlayerStats  fetch particular score card for a individual player, function called from initialisePlayerStats.
     * @param  inning1 :
     * @param inning2:
     * @param player : name of player for which score card needed
     */

    public PlayerStatsInSingleMatch getPlayerStats (Inning inning1, Inning inning2 , Player player) {

        PlayerStatsInSingleMatch playerStatsInSingleMatch = null;

        int runsScored = PlayerUtil.getPlayerWiseScore(player, inning1) + PlayerUtil.getPlayerWiseScore(player, inning2);
        int centuries = PlayerUtil.getCenturies(player, inning1) + PlayerUtil.getCenturies(player, inning2);
        int sixes = PlayerUtil.getSixes(player, inning1) + PlayerUtil.getSixes(player, inning2);
        int fours = PlayerUtil.getFours(player, inning1) + PlayerUtil.getFours(player, inning2);
        int ballsPlayed = PlayerUtil.getTotalBallsPlayed(player, inning1) + PlayerUtil.getTotalBallsPlayed(player, inning2);

        int wicketTaken = PlayerUtil.getWicketTakenByBowler(player, inning1) + PlayerUtil.getWicketTakenByBowler(player, inning2);
        int ballsBowled = PlayerUtil.getBallsBowledByBowler(player, inning1) + PlayerUtil.getBallsBowledByBowler(player, inning2);

        BattingStatsOfPlayer battingStatsOfPlayer = new BattingStatsOfPlayer(runsScored, centuries, sixes, fours, ballsPlayed);
        BowlingStatsOfPlayer bowlingStatsOfPlayer = new BowlingStatsOfPlayer(wicketTaken, ballsBowled);

        playerStatsInSingleMatch = new PlayerStatsInSingleMatch(player.getPlayerName(), battingStatsOfPlayer, bowlingStatsOfPlayer);

        return playerStatsInSingleMatch;

    }


}
