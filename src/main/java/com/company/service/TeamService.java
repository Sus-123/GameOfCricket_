package com.company.service;


import com.company.entity.matchEntity.Team;
import com.company.repozitory.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;


    public Team getTeamDetails(String teamName) {

        if(!teamRepository.checkIfTeamExist(teamName)) {
            throw new IllegalStateException("Team with name "+ teamName + " does not exist in Database");
        }

        Team team = null;
        int teamId = teamRepository.getTeamIdFromTeamName(teamName);
        team = teamRepository.getTeam(teamId);
        return team;
    }
}
