package com.company.service;

import com.company.entity.matchEntity.Inning;
import com.company.entity.matchEntity.Player;
import com.company.entity.responseEntity.BattingStatsOfPlayer;
import com.company.entity.responseEntity.BowlingStatsOfPlayer;
import com.company.entity.responseEntity.PlayerStatsInSingleMatch;
import com.company.repozitory.InningRepository;
import com.company.repozitory.MatchRepository;
import com.company.repozitory.PlayersRepository;
import com.company.util.PlayerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;


@Service
public class PlayerService {

    @Autowired
    private PlayersRepository playersRepository;
    @Autowired
    MatchRepository matchRepository;

    @Autowired
    InningRepository inningRepository;



    public PlayerStatsInSingleMatch initialisePlayerStats(String matchName, String teamName, String playerName) {

        int matchId  = 0;
        int playerId = 0;
        try {
            matchId = matchRepository.getMatchIdByName(matchName);
            playerId = playersRepository.getPlayerId(playerName, teamName);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            ArrayList<Integer> inningsIds = matchRepository.getInningId(matchId);

            Inning inning1 = inningRepository.createInning(inningsIds.get(0));
            Inning inning2 = inningRepository.createInning(inningsIds.get(1));


            Player player = playersRepository.createPlayer(playerId);


           // String playerName = player.getPlayerName();
            int runsScored = PlayerUtil.getPlayerWiseScore(player, inning1) + PlayerUtil.getPlayerWiseScore(player, inning2);
            int centuries = PlayerUtil.getCenturies(player, inning1) + PlayerUtil.getCenturies(player, inning2);
            int sixes = PlayerUtil.getSixes(player, inning1) + PlayerUtil.getSixes(player, inning2);
            int fours = PlayerUtil.getFours(player, inning1) + PlayerUtil.getFours(player, inning2);
            int ballsPlayed = PlayerUtil.getTotalBallsPlayed(player, inning1) + PlayerUtil.getTotalBallsPlayed(player, inning2);


            int wicketTaken = PlayerUtil.getWicketTakenByBowler(player, inning1) + PlayerUtil.getWicketTakenByBowler(player, inning2);
            int ballsBowled = PlayerUtil.getBallsBowledByBowler(player, inning1) + PlayerUtil.getBallsBowledByBowler(player, inning2);


            BattingStatsOfPlayer battingStatsOfPlayer = new BattingStatsOfPlayer(runsScored, centuries, sixes, fours, ballsPlayed);
            BowlingStatsOfPlayer bowlingStatsOfPlayer = new BowlingStatsOfPlayer(wicketTaken, ballsBowled);

            PlayerStatsInSingleMatch playerStatsInSingleMatch = new PlayerStatsInSingleMatch(playerName, battingStatsOfPlayer, bowlingStatsOfPlayer);

            return playerStatsInSingleMatch;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
