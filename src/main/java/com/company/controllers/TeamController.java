package com.company.controllers;

import com.company.constants.Constants;
import com.company.entity.matchEntity.Team;
import com.company.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @GetMapping("/team/{teamName}")
    public ResponseEntity<Team> getTeamDetails (@PathVariable("teamName") String teamName) {
        Team team = null;

        team = teamService.getTeamDetails(teamName);
        if(team.equals(Constants.notExist)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(team, HttpStatus.OK);
        }
    }

}
