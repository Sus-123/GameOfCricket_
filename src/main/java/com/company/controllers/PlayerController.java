package com.company.controllers;
import com.company.entity.responseEntity.PlayerStatsInSingleMatch;
import com.company.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/match/{matchName}/team/{teamName}/player/{playerName}")
    public ResponseEntity<PlayerStatsInSingleMatch> getPlayerInfoFromMatchName(@PathVariable("matchName") String matchName, @PathVariable("teamName") String teamName, @PathVariable("playerName") String playerName)  {

        PlayerStatsInSingleMatch playerStatsInSingleMatch = playerService.initialisePlayerStats(matchName,teamName, playerName);
        if(playerStatsInSingleMatch == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(playerStatsInSingleMatch, HttpStatus.OK);
        }
    }

}
