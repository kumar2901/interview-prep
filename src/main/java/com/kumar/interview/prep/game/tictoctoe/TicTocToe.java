package com.kumar.interview.prep.game.tictoctoe;

import com.kumar.interview.prep.game.tictoctoe.model.GameMode;

import java.util.Scanner;

public class TicTocToe {
    static void main() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Who do you want to play with ?");
        System.out.println("1. Human");
        System.out.println("2. AI");
        System.out.print("Enter choice: ");

        int choice = sc.nextInt();
        GameMode mode = (choice == 2) ? GameMode.AI : GameMode.HUMAN;

        new Game(mode).start();
    }

}
