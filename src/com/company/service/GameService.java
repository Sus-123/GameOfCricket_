package com.company.service;
import com.company.Constants;
import com.company.entity.Inning;
import com.company.entity.Team;
import com.company.util.Util;

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

    public void initializeNewGame(Team team1, Team team2, int numOfOver) {
        //System.out.println(team1.getName() + team2.getName());


        if(Util.playToss() == Constants.ZERO) {
            inning1 = new Inning(team1, team2, false, 0, numOfOver);
            gameServiceHelper.playInning(inning1);
            System.out.println(team1.getName() + " ended game with " + inning1.score);

            inning2 = new Inning(team2, team1, true,inning1.score, numOfOver);
            gameServiceHelper.playInning(inning2);
            System.out.println(team2.getName() + " ended game with " + inning2.score);
        }
        else {
            inning1 = new Inning(team2, team1, false, 0, numOfOver);
            gameServiceHelper.playInning(inning1);
            System.out.println(team2.getName() + " ended game with " + inning1.score);

            inning2 = new Inning(team1, team2, true, inning1.score, numOfOver);
            gameServiceHelper.playInning(inning2);
            System.out.println(team1.getName() + " ended game with " + inning2.score);
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
