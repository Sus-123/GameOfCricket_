package com.company.service;
import com.company.Constants;
import com.company.entity.Inning;
import com.company.entity.Team;
import com.company.util.Util;

public class GameServiceHelper {
    GameService gameService;


    /**
     * instantiateNewGame  will initialise a new match, between two team
     * @param team1
     * @param team2
     * @param numOfOver : total numb of over to be played
     */

    public void initializeNewGame(Team team1, Team team2, int numOfOver) {

        if(Util.playToss() == Constants.ZERO) {
            GameService firstInning = new GameService(team1,team2,false,0, numOfOver);
            firstInning.playInning();
            GameService secondInning = new GameService(team2,team1,true, firstInning.inning.getScore(), numOfOver);
            secondInning.playInning();
        }
        else {
            GameService firstInning = new GameService(team2,team1,false,0, numOfOver);
            firstInning.playInning();
            GameService secondInning = new GameService(team1,team2,true, firstInning.inning.getScore(), numOfOver);
            secondInning.playInning();

        }
    }

    public void showFinalScoreBoard () {

    }



//
//    /**
//     * initializeTeamPlayer : will instantiate players of each team.
//     * @param playersName : Name of each player
//     * @param playersType : Type of each player
//     */
//    public void initializeTeamPlayer(List<String> playersName, List<String> playersType, int numberOfPlayer) {
//        for (int i = 0; i < numberOfPlayer; i++) {
//            System.out.print("Player-" + i + " Name:");
//            playersName.add(gameUtil.getValidStringType());
//            System.out.print("Player-" + i + " Type:");
//            playersType.add(gameUtil.getPlayerType());
//        }
//        //System.out.println("Size of team name" + playersName.size());
//       // System.out.println("size of type :" + playersType.size());
//
//    }

}
