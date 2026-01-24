package com.kumar.interview.prep.game.battleship.service;

import com.kumar.interview.prep.game.battleship.core.GameManager;
import com.kumar.interview.prep.game.battleship.strategy.RandomFiringStrategy;

public class BattleShipGameService {

    private GameManager game;

    public void initGame(int size) {
        if (size % 2 == 1) {
            throw new RuntimeException("Battlefield size must be even");
        }
        game = new GameManager(size, new RandomFiringStrategy());
    }

    public void addShip(String id, int size, int ax, int ay, int bx, int by) {
        game.addShip(id, size, ax, ay, bx, by);
    }

    public void viewBattleField() {
        game.printBoard();
    }

    public void startGame() {
        game.startGame();
    }
}
