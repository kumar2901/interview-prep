package com.kumar.interview.prep.game.battleship.core;

import com.kumar.interview.prep.game.battleship.model.BattleField;
import com.kumar.interview.prep.game.battleship.model.Ship;
import com.kumar.interview.prep.game.battleship.strategy.FiringStrategy;
import lombok.Data;

@Data

public class GameManager {

    private final int size;
    private final BattleField fieldA;
    private final BattleField fieldB;
    private final FiringStrategy firingStrategy;

    public GameManager(int size, FiringStrategy firingStrategy) {
        this.size = size;
        fieldA = new BattleField(size, 0, size / 2, "PlayerA");
        fieldB = new BattleField(size, size / 2, size, "PlayerB");
        this.firingStrategy = firingStrategy;

    }

    public void addShip(String id, int size, int ax, int ay, int bx, int by) {
        fieldA.addShip(new Ship(id,size,ax,ay));
        fieldB.addShip(new Ship(id,size,bx,by));
    }
    public void printBoard(){
        fieldA.print();
        fieldB.print();
    }

    public void viewBattleField() {
        int N = this.size;
        String[][] view = new String[N][N];

        // Initialize empty grid
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                view[y][x] = ".";
            }
        }

        // Fill PlayerA ships
        fillShips(view, fieldA, "A");

        // Fill PlayerB ships
        fillShips(view, fieldB, "B");

        // Print battlefield
        System.out.println("\n======= BATTLEFIELD VIEW =======");
        for (int y = N - 1; y >= 0; y--) {
            System.out.printf("%2d | ", y);
            for (int x = 0; x < N; x++) {
                if (x == N / 2) System.out.print("| ");
                System.out.printf("%-5s", view[y][x]);
            }
            System.out.println();
        }

        // X-axis
        System.out.print("    ");
        for (int x = 0; x < N; x++) {
            if (x == N / 2) System.out.print("  ");
            System.out.printf("%-5d", x);
        }
        System.out.println("\n       PlayerA        PlayerB\n");
    }

    private void fillShips(String[][] view, BattleField field, String prefix) {
        for (Ship ship : field.getShips().values()) {
            for (String cell : ship.getCells()) {
                String[] p = cell.split(",");
                int x = Integer.parseInt(p[0]);
                int y = Integer.parseInt(p[1]);
                view[y][x] = prefix + "-" + ship.getId();
            }
        }
    }


    public void startGame() {
    }
}
