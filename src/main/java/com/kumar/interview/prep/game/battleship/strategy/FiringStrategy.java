package com.kumar.interview.prep.game.battleship.strategy;

import com.kumar.interview.prep.game.battleship.model.Coordinate;

public interface FiringStrategy {

    Coordinate fire(int size, int xStart, int xEnd);
}
