package com.company.entity.matchEntity;

import lombok.Data;

@Data
public class BallDetails {

    private BallType ballType;
    private Player strikerOnBall;
    private int scoreOnBall;
    public BallDetails () {
        this.scoreOnBall = 0;
    }

}
