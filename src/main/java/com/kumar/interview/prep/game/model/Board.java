package com.kumar.interview.prep.game.model;

public class Board {
    private final char[][] grid = new char[3][3];

    public Board() {
        reset();
    }

    public void reset() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                grid[i][j] = ' ';
    }

    public boolean placeMove(int pos, char symbol) {
        int r = (pos - 1) / 3;
        int c = (pos - 1) % 3;

        if (pos < 1 || pos > 9 || grid[r][c] != ' ')
            return false;

        grid[r][c] = symbol;
        return true;
    }

    public boolean isFull() {
        for (char[] row : grid)
            for (char c : row)
                if (c == ' ')
                    return false;
        return true;
    }

    public char[][] getGrid() {
        return grid;
    }

    public boolean checkWin(char p) {
        for (int i = 0; i < 3; i++)
            if ((grid[i][0] == p && grid[i][1] == p && grid[i][2] == p)
                    || (grid[0][i] == p && grid[1][i] == p && grid[2][i] == p))
                return true;

        return (grid[0][0] == p && grid[1][1] == p && grid[2][2] == p)
                || (grid[0][2] == p && grid[1][1] == p && grid[2][0] == p);
    }

    public void print() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            System.out.print(" ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grid[i][j]);
                if (j < 2)
                    System.out.print(" | ");
            }
            System.out.println();
            if (i < 2)
                System.out.println("---|---|---");
        }
        System.out.println();
    }
}
