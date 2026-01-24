package com.kumar.interview.prep.game.battleship;

import com.kumar.interview.prep.game.battleship.service.BattleShipGameService;

public class BattleShipGameApplication {

    static void main() {
        BattleShipGameService gameService = new BattleShipGameService();

        gameService.initGame(6);

        gameService.addShip("SH1", 2, 1, 5, 4, 4);

        gameService.viewBattleField();

        gameService.startGame();
        gameService.viewBattleField();

    }
}
