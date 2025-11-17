package com.kumar.interview.prep.interview_prep.dsa.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NKnights {

    boolean[][] board;
    List<List<String>> result;
    int totalCount = 0;

    /**
     * Place N knights on an N×N board such that no two knights attack each other.
     *
     * <p>
     * n=1 1 <br/>
     * n=2 6 <br/>
     * n=3 36 <br/>
     * n=4 412 <br/>
     * </p>
     */
    public List<List<String>> nKnights(int n) {

        result = new ArrayList<>();
        board = new boolean[n][n];
        placeNKnights(0, 0, n);
        return result;

    }

    private void placeNKnights(int row, int col, int knights) {
        if (knights == 0) {
            addBoard(board, result);
            totalCount++;
            return;
        }
        if (row == board.length) {
            return;
        }
        int nextRow = (col >= board.length - 1 ? row + 1 : row);
        int nextCol = (col + 1) % board.length;

        if (isSafe(board, row, col)) {
            board[row][col] = true;
            placeNKnights(nextRow, nextCol, knights - 1);

            board[row][col] = false;
        }

        placeNKnights(nextRow, nextCol, knights);

    }

    private boolean isSafe(boolean[][] board, int row, int col) {

        // all knights moves
        int[][] moves = {{-2, -1}, {-2, +1}, {-1, -2}, {-1, +2}, {+1, -2}, {+1, +2}, {+2, -1}, {+2, +1}};

        for (int[] m : moves) {
            int r = row + m[0];
            int c = col + m[1];
            if (r >= 0 && r < board.length && c >= 0 && c < board.length) {
                if (board[r][c]) {
                    return false;
                }
            }
        }

        return true;

    }

    private void addBoard(boolean[][] board, List<List<String>> result) {
        List<String> chessRows = new ArrayList<>();
        for (boolean[] rows : board) {
            StringBuilder sb = new StringBuilder();
            for (boolean cell : rows) {
                if (cell) {
                    sb.append("K");
                } else {
                    sb.append(".");
                }
            }
            chessRows.add(sb.toString());
        }
        result.add(chessRows);
    }

    public static void main(String[] args) {

        NKnights nKnights2 = new NKnights();

        System.out.println(nKnights2.nKnights(2));
        System.out.println(nKnights2.totalCount);



        NKnights nKnights3 = new NKnights();
        System.out.println(nKnights3.nKnights(3));
        System.out.println(nKnights3.totalCount);



        NKnights nKnights4 = new NKnights();
        System.out.println(nKnights4.nKnights(4));
        System.out.println(nKnights4.totalCount);

    }
}
