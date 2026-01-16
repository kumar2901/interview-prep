package com.kumar.interview.prep.game.model;

import lombok.Getter;

@Getter
public class Player {

    private final char symbol;
    private final boolean isAI;

    public Player(char symbol, boolean isAI) {
        this.symbol = symbol;
        this.isAI = isAI;
    }

}
