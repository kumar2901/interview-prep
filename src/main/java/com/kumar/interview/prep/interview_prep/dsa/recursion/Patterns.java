package com.kumar.interview.prep.interview_prep.dsa.recursion;

public class Patterns {

    /**

     * * * * *
     * * * *
     * * *
     * *
     *

     */
    private void printTriangle(int r, int c){
        if(r==0){
            return;
        }
        if(c<r){
            System.out.print("* ");
            printTriangle(r,c+1);
        }else{
            System.out.println();
            printTriangle(r-1,0);
        }
    }

    /*
        *
       * *
      * * *
     * * * *
    * * * * *

     */

    private void printPyramid(int n){
        if(n==0){
            return;
        }
        printPyramidHelper(n,1);

    }

    private void printPyramidHelper(int totalRows, int currentRow) {
        if (currentRow > totalRows) {
            return;
        }
        printSpaces(totalRows - currentRow, 0);
        printStars(currentRow, 0);
        System.out.println();
        printPyramidHelper(totalRows, currentRow + 1);
    }

    private void printSpaces(int remaining, int c) {
        if (c >= remaining) return;
        System.out.print(" ");
        printSpaces(remaining, c + 1);
    }

    private void printStars(int count, int c) {
        if (c >= count) return;
        System.out.print("*");
        if (c < count - 1) System.out.print(" ");
        printStars(count, c + 1);
    }


    public void main(String[] args) {
        printTriangle(5,0);
        printPyramid(5);
    }
}
