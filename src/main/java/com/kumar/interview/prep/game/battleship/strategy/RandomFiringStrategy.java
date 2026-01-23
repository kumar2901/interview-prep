package com.kumar.interview.prep.game.battleship.strategy;

import com.kumar.interview.prep.game.battleship.model.Coordinate;
import com.kumar.interview.prep.game.battleship.model.MissileTracker;

import java.util.Random;

public class RandomFiringStrategy implements FiringStrategy {

    private Random random = new Random();

    @Override
    public Coordinate fire(int size, int xStart, int xEnd) {
        int x, y;
        do {
            x = random.nextInt(xEnd - xStart) + xStart;
            y = random.nextInt(size);
        } while (MissileTracker.alreadyFired(x, y));

        MissileTracker.markFired(x, y);
        return new Coordinate(x, y);
    }
}
