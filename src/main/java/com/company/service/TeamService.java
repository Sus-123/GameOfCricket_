package com.company.service;


import com.company.Exception.GameExceptions;
import com.company.entity.matchEntity.Team;
import com.company.repozitory.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;


    /**
     * getTeamDetails  will get details of a team including team name and its players details.
     * @param teamName: team from which details needed
     */
    public Team getTeamDetails(String teamName) {

        if(!teamRepository.checkIfTeamExist(teamName)) {
            throw new GameExceptions("Team with name "+ teamName + " does not exist in Database", HttpStatus.NOT_FOUND);
        }

        Team team = null;
        int teamId = teamRepository.getTeamIdFromTeamName(teamName);
        team = teamRepository.getTeam(teamId);
        return team;
    }
}
