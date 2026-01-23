package com.kumar.interview.prep.game.battleship.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class Ship {

    private String id;
    private boolean destroyed = false;
    private Set<String> cells = new HashSet<>();

    public Ship(String id, int size, int x, int y) {
        this.id = id;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j > y - size; j--) {
                cells.add(i + "," + j);
            }
        }
    }
}
