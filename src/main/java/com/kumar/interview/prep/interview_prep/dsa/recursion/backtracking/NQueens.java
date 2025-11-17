package com.kumar.interview.prep.interview_prep.dsa.recursion.backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {

    /**
     * <p>
     * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each
     * other. Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any
     * order. Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both
     * indicate a queen and an empty space, respectively.
     * </p>
     *
     * @param n
     *            size of grid nxn
     * @return result
     *
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        boolean[][] board = new boolean[n][n];

        placeQueens(board, 0, result);
        return result;
    }

    private void placeQueens(boolean[][] board, int row, List<List<String>> result) {

        if (row == board.length) {
            display(board, result);
        }
        for (int c = 0; c < board.length; c++) {
            if (isSafe(board, row, c)) {
                board[row][c] = true;
                placeQueens(board, row + 1, result);

                // backtrack
                board[row][c] = false;

            }
        }

    }

    private boolean isSafe(boolean[][] board, int row, int c) {

        // check vertical or same column
        for (int i = 0; i < row; i++) {
            if (board[i][c]) {
                return false;
            }

        }

        // same diagonal : up left

        int i = row, j = c;
        while (i >= 0 && j >= 0) {
            if (board[i][j]) {
                return false;
            }
            i--;
            j--;
        }

        // reverse diagonal : up right
        i = row;
        j = c;
        while (i >= 0 && j < board.length) {
            if (board[i][j]) {
                return false;
            }
            i--;
            j++;

        }

        return true;
    }

    private void display(boolean[][] board, List<List<String>> result) {
        List<String> queens = new ArrayList<>();
        for (boolean[] booleans : board) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < board.length; j++) {
                if (booleans[j]) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
            queens.add(sb.toString());
        }

        result.add(queens);
    }

    public void main(String[] args) {
        System.out.println(solveNQueens(4));

        System.out.println(solveNQueens(5));
    }
}
