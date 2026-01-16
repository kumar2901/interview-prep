package com.kumar.interview.prep.game;

import com.kumar.interview.prep.game.model.Board;
import com.kumar.interview.prep.game.model.GameMode;
import com.kumar.interview.prep.game.model.Player;

import java.util.Scanner;

public class Game {
    private final Board board;
    private final Player p1;
    private final Player p2;
    private final GameMode mode;
    private final MinimaxAI ai = new MinimaxAI();

    public Game(GameMode mode) {
        this.mode = mode;
        board = new Board();
        p1 = new Player('X', false);
        p2 = new Player('O', mode == GameMode.AI);
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        Player current = p1;

        while (true) {
            board.print();

            if (current.isAI()) {
                int move = ai.bestMove(board);
                board.placeMove(move, current.getSymbol());
                System.out.println("AI chose: " + move);
            } else {
                System.out.print("Player " + current.getSymbol() + " move (1-9): ");
                int move = sc.nextInt();
                if (!board.placeMove(move, current.getSymbol())) {
                    System.out.println("Invalid move!");
                    continue;
                }
            }

            if (board.checkWin(current.getSymbol())) {
                board.print();
                System.out.println("🎉 Player " + current.getSymbol() + " wins!");
                break;
            }

            if (board.isFull()) {
                board.print();
                System.out.println("🤝 Draw!");
                break;
            }

            current = (current == p1) ? p2 : p1;
        }
    }
}
