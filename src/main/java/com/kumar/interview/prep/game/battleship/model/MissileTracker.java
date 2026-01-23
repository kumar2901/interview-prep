package com.kumar.interview.prep.game.battleship.model;

import java.util.HashSet;
import java.util.Set;

public class MissileTracker {

    private static final Set<String> fired = new HashSet<>();

    public static boolean alreadyFired(int x, int y) {
        return fired.contains(x + "," + y);
    }

    public static void markFired(int x, int y) {
        fired.add(x + "," + y);
    }
}
