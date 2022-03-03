package com.company.scoreboard;
import com.company.Constants;
import com.company.entity.Inning;
import com.company.repozitory.*;
import java.sql.SQLException;
import java.util.ArrayList;


public class PreviousMatchResult {


    public static void displayPreviouslyPlayedMatch (String matchName) throws SQLException, ClassNotFoundException {

        int matchId = MatchRepository.getMatchIdByName(matchName);

        if(matchId == Constants.notExist) {
            System.out.println("Match does not exist");
            return;
        }

        ArrayList<Integer> inningIds = MatchRepository.getInningId(matchId);

        Inning inning1 =  InningRepository.createInning(inningIds.get(0));
        Inning inning2 = InningRepository.createInning(inningIds.get(1));

        ScoreBoard.showMatchResult(inning1, inning2);

    }


}
