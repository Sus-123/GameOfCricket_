package com.company.controllers;
import com.company.constants.Constants;
import com.company.request.Match;
import com.company.response.MatchStats;
import com.company.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @GetMapping("/matchResult/{matchName}")
    public ResponseEntity<MatchStats> getMatchByName (@PathVariable("matchName") String matchName) {
        MatchStats matchStats = matchService.getMatch(matchName);
        if(matchStats.equals(Constants.notExist)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(matchStats, HttpStatus.OK);
        }
    }

    @PostMapping("/play/match")
    public ResponseEntity<MatchStats> playMatch (@RequestBody Match match) {
        MatchStats matchStats = matchService.playMatch(match.getOvers(), match.getTeamAName(), match.getTeamBName(), match.getMatchName());

        if (matchStats.equals(Constants.notExist)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(matchStats, HttpStatus.CREATED);
        }
    }


}
