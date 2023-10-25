package org.dinosat;

import java.util.*;


import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class Main {
    private static final Map<String, Integer> leaderboard = new HashMap<>();
    public static int currentScore = 0;

    public static void main(String[] args) {
        PinballMachine pinballMachine = PinballMachine.getInstance();
        runPinballGame(pinballMachine);
    }

    private static void runPinballGame(PinballMachine pinballMachine) {
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

    private static int showMainMenuAndGetChoice(Scanner scanner) {
        System.out.println("Choose an action:");
        System.out.println("1. Insert coin");
        System.out.println("2. Press start button");
        System.out.println("3. Quit");

        return scanner.nextInt();
    }

    private static boolean startGame(PinballMachine pinballMachine, boolean isGameRunning) {
        if (pinballMachine.getState() instanceof NoCreditState) {
            System.out.println("No credit available. Please insert a coin.");
        } else if (pinballMachine.getState() instanceof ReadyState) {
            pinballMachine.getState().pressStartButton();
            System.out.println("Game started!");
            isGameRunning = true;
        } else {
            System.out.println("The game is already in progress.");
        }
        return isGameRunning;
    }

    private static boolean playGame(PinballMachine pinballMachine, Scanner scanner) {
        System.out.println("Choose an action:");
        System.out.println("1. Hit Ramp");
        System.out.println("2. Hit Target");
        System.out.println("3. Lose Ball");
        System.out.println("4. Quit");

        int action = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (action) {
            case 1:
                handleRampHit(pinballMachine);
                break;
            case 2:
                handleTargetHit(pinballMachine);
                break;
            case 3:
                return handleBallLoss(pinballMachine, scanner);
            case 4:
                handleGameEnd(pinballMachine, scanner);
                return false;
            default:
                System.out.println("Invalid action. Please try again.");
                break;
        }
        return true;
    }

    private static void handleRampHit(PinballMachine pinballMachine) {
        // Simulate hitting the ramp
        Command openRamp = new OpenRampCommand(new Ramp());
        openRamp.execute();
        new Ramp().accept(new ResetVisitor());
    }

    private static void handleTargetHit(PinballMachine pinballMachine) {
        // Simulate hitting the target
        Command awardPoints = new AwardPointsCommand(new Target(), 100);
        awardPoints.execute();
        awardPoints.toString();
    }

    private static boolean handleBallLoss(PinballMachine pinballMachine, Scanner scanner) {
        pinballMachine.getState().ballLost();
        if (pinballMachine.getBallsLost() >= pinballMachine.getMaxBalls()) {
            handleGameEnd(pinballMachine, scanner);
            return false;
        }
        return true;
    }

    private static void handleGameEnd(PinballMachine pinballMachine, Scanner scanner) {
        // Handle game ending, update leaderboard and reset score
        pinballMachine.setBallsLost(0);
        if (pinballMachine.getCredits() >= 1) {
            pinballMachine.setMaxBalls(3);
            pinballMachine.setState(new ReadyState(pinballMachine));
        } else {
            pinballMachine.setMaxBalls(3);
            pinballMachine.setState(new NoCreditState(pinballMachine));
        }
        pinballMachine.decrementCredits();

        System.out.println("Enter your name: ");
        String playerName = scanner.nextLine();
        leaderboard.put(playerName, currentScore);

        System.out.println("Game Over. Your score is: " + currentScore);
        currentScore = 0;
    }

    private static boolean exitGameAndShowLeaderboard(Map<String, Integer> leaderboard) {
        printLeaderboard(leaderboard);
        return false;
    }

    public static void printLeaderboard(Map<String, Integer> leaderboard) {
        List<Map.Entry<String, Integer>> sortedLeaderboard = new ArrayList<>(leaderboard.entrySet());
        sortedLeaderboard.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        System.out.println("\n" + " .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" + "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" + "| |   _____      | || |  _________   | || |      __      | || |  ________    | || |  _________   | || |  _______     | || |   ______     | || |     ____     | || |      __      | || |  _______     | || |  ________    | || |              | |\n" + "| |  |_   _|     | || | |_   ___  |  | || |     /  \\     | || | |_   ___ `.  | || | |_   ___  |  | || | |_   __ \\    | || |  |_   _ \\    | || |   .'    `.   | || |     /  \\     | || | |_   __ \\    | || | |_   ___ `.  | || |      _       | |\n" + "| |    | |       | || |   | |_  \\_|  | || |    / /\\ \\    | || |   | |   `. \\ | || |   | |_  \\_|  | || |   | |__) |   | || |    | |_) |   | || |  /  .--.  \\  | || |    / /\\ \\    | || |   | |__) |   | || |   | |   `. \\ | || |     (_)      | |\n" + "| |    | |   _   | || |   |  _|  _   | || |   / ____ \\   | || |   | |    | | | || |   |  _|  _   | || |   |  __ /    | || |    |  __'.   | || |  | |    | |  | || |   / ____ \\   | || |   |  __ /    | || |   | |    | | | || |      _       | |\n" + "| |   _| |__/ |  | || |  _| |___/ |  | || | _/ /    \\ \\_ | || |  _| |___.' / | || |  _| |___/ |  | || |  _| |  \\ \\_  | || |   _| |__) |  | || |  \\  `--'  /  | || | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |  _| |___.' / | || |     (_)      | |\n" + "| |  |________|  | || | |_________|  | || ||____|  |____|| || | |________.'  | || | |_________|  | || | |____| |___| | || |  |_______/   | || |   `.____.'   | || ||____|  |____|| || | |____| |___| | || | |________.'  | || |              | |\n" + "| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |\n" + "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" + " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n");
        for (int i = 0; i < sortedLeaderboard.size(); i++) {
            Map.Entry<String, Integer> entry = sortedLeaderboard.get(i);
            String playerName = entry.getKey();
            int score = entry.getValue();

            String rank = String.format("%2d. ", i + 1);
            String scoreText = String.format("%4d", score);

            String leaderboardEntry = String.format("%-5s %-6s: %10s", rank, playerName, scoreText);
            System.out.println(leaderboardEntry);
        }
    }
}
