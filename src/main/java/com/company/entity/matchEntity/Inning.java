package com.company.entity.matchEntity;
import lombok.Data;
import java.util.ArrayList;


@Data
public class Inning {

    private Team battingTeam;
    private Team bowlingTeam;
    private boolean isChaser;
    private int scoreToChase;
    private int numOfOver;
    public Strike strike;
    private ArrayList<OverDetails> overDetails;

    public Inning (Team battingTeam, Team ballingTeam, boolean isChaser, int scoreToChase, int numOfOver, Strike strike) {

        this.battingTeam = battingTeam;
        this.bowlingTeam = ballingTeam;
        this.isChaser = isChaser;
        this.scoreToChase = scoreToChase;
        this.numOfOver = numOfOver;
        this.strike = strike;
        this.overDetails = new ArrayList<>();
    }

}
