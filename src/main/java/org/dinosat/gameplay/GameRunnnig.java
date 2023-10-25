package org.dinosat.gameplay;

import org.dinosat.gamestates.PinballMachine;

import java.util.Scanner;


import static org.dinosat.Main.leaderboard;
import static org.dinosat.gameplay.Gameplay.*;
import static org.dinosat.gameplay.Leaderboard.exitGameAndShowLeaderboard;

public class GameRunnnig {
    public static void runPinballGame(PinballMachine pinballMachine) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        boolean isGameRunning = false;

        while (isRunning) {
            if (isGameRunning) {
                isGameRunning = playGame(pinballMachine, scanner);
            } else {
                int choice = showMainMenuAndGetChoice(scanner);

                switch (choice) {
                    case 1:
                        pinballMachine.insertCoin();
                        break;
                    case 2:
                        isGameRunning = startGame(pinballMachine, isGameRunning);
                        break;
                    case 3:

                        isRunning = exitGameAndShowLeaderboard(leaderboard);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
        scanner.close();
    }
}
