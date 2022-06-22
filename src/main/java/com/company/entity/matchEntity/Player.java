package com.company.entity.matchEntity;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Player {

    private String playerName ;
    private PlayerType playerType;

}
