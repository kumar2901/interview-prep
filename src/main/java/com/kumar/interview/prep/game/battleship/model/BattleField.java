package com.kumar.interview.prep.game.battleship.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class BattleField {

    private int N;
    private int xStart, xEnd;
    private String owner;
    private String[][] grid;
    private Map<String, Ship> ships = new HashMap<>();

    public BattleField(int N, int xStart, int xEnd, String owner) {
        this.N = N;
        this.xStart = xStart;
        this.xEnd = xEnd;
        this.owner = owner;
        grid = new String[N][N];
    }

    public void addShip(Ship ship) {
        for (String c : ship.getCells()) {
            String[] p = c.split(",");
            int x = Integer.parseInt(p[0]);
            int y = Integer.parseInt(p[1]);

            if (x < xStart || x >= xEnd || y < 0 || y >= N)
                throw new RuntimeException("Ship out of bounds");

            if (grid[y][x] != null)
                throw new RuntimeException("Ship overlap");

            grid[y][x] = ship.getId();
        }
        ships.put(ship.getId(), ship);
    }

    public Ship fire(int x, int y) {
        String id = grid[y][x];
        if (id == null)
            return null;

        Ship s = ships.get(id);
        if (!s.isDestroyed()) {
            s.setDestroyed(true);
            return s;
        }
        return null;
    }

    public boolean allShipsDestroyed() {
        return ships.values().stream().allMatch(Ship::isDestroyed);
    }

    public void print() {
        System.out.println("BattleField " + owner);
        for (int y = N - 1; y >= 0; y--) {
            System.out.print(y + " | ");
            for (int x = xStart; x < xEnd; x++) {
                if (grid[y][x] == null)
                    System.out.print(". ");
                else
                    System.out.print(owner + "-" + grid[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
