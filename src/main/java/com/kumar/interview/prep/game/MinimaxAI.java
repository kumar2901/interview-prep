package com.kumar.interview.prep.game;

import com.kumar.interview.prep.game.model.Board;

public class MinimaxAI {

    public int bestMove(Board board) {
        int bestVal = Integer.MIN_VALUE;
        int bestMove = -1;

        char[][] g = board.getGrid();

        for (int i = 0; i < 9; i++) {
            int r = i / 3;
            int c = i % 3;

            if (g[r][c] == ' ') {
                g[r][c] = 'O';
                int moveVal = minimax(board, false);
                g[r][c] = ' ';

                if (moveVal > bestVal) {
                    bestVal = moveVal;
                    bestMove = i + 1;
                }
            }
        }
        return bestMove;
    }

    private int minimax(Board board, boolean isMax) {
        if (board.checkWin('O'))
            return 10;
        if (board.checkWin('X'))
            return -10;
        if (board.isFull())
            return 0;

        char[][] g = board.getGrid();
        int best = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 9; i++) {
            int r = i / 3;
            int c = i % 3;

            if (g[r][c] == ' ') {
                g[r][c] = isMax ? 'O' : 'X';
                int val = minimax(board, !isMax);
                g[r][c] = ' ';
                best = isMax ? Math.max(best, val) : Math.min(best, val);
            }
        }
        return best;
    }
}
