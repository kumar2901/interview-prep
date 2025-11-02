package com.kumar.interview.prep.interview_prep.dsa.recursion;

public class Patterns {

    /**
     *
     * * * * * * * * * * *
     *
     *
     */
    private void printTriangle(int r, int c) {
        if (r == 0) {
            return;
        }
        if (c < r) {
            System.out.print("* ");
            printTriangle(r, c + 1);
        } else {
            System.out.println();
            printTriangle(r - 1, 0);
        }
    }

    /*
     *
     * * * * * * * * * * *
     *
     */

    private void printPyramid(int n) {
        if (n == 0) {
            return;
        }
        printPyramidHelper(n, 1);

    }

    private void printPyramidHelper(int totalRows, int currentRow) {
        if (currentRow > totalRows) {
            return;
        }
        printSpaces(totalRows - currentRow);
        printStars(currentRow);
        System.out.println();
        printPyramidHelper(totalRows, currentRow + 1);
    }

    private void printStars(int currentRow) {
        if (currentRow == 0) {
            return;
        }
        System.out.print("* ");
        printStars(currentRow - 1);
    }

    /*
     * 5-1 4 space 5-2 3 5-3 5-4 5-5
     */
    private void printSpaces(int remainingRows) {
        if (remainingRows <= 0) {
            return;
        }
        System.out.print(" ");
        printSpaces(remainingRows - 1);
    }

    public void main(String[] args) {
        printTriangle(5, 0);
        printPyramid(5);
    }
}
