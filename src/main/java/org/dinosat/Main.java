package org.dinosat;

import java.util.*;

public class Main {
    private static Map<String, Integer> leaderboard = new HashMap<>(); // Create a leaderboard map to store scores.
    private static int score = 0;
    public static void main(String[] args) {
        PinballMachine pinballMachine = PinballMachine.getInstance();
        setupGame(pinballMachine);
        playGame(pinballMachine);
    }

    private static void setupGame(PinballMachine pinballMachine) {
        PinballElement ramp = new Ramp();
        PinballElement target = new Target();
        Visitor pointVisitor = new PointVisitor();
        Visitor resetVisitor = new ResetVisitor();

        ramp.accept(pointVisitor); // Calculate points for hitting the ramp
        target.accept(pointVisitor); // Calculate points for hitting the target

        ramp.accept(resetVisitor); // Reset the ramp
        target.accept(resetVisitor); // Reset the target

        pinballMachine.addElement(ramp);
        pinballMachine.addElement(target);
    }

    private static void playGame(PinballMachine pinballMachine) {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        boolean isGameRunning = false;
        Visitor pointVisitor = new PointVisitor();
        Visitor resetVisitor = new ResetVisitor();
        while (isRunning) {
            if (isGameRunning) {
                System.out.println("Choose an action:");
                System.out.println("1. Hit Ramp");
                System.out.println("2. Hit Target");
                System.out.println("3. Lose Ball");
                System.out.println("4. Quit");

                int action = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (action) {
                    case 1:
                        // Simulate hitting the ramp
                        Command openRamp = new OpenRampCommand(new Ramp());
                        openRamp.execute();
                        new Ramp().accept(pointVisitor);
                        new Ramp().accept(resetVisitor);
                        break;
                    case 2:
                        // Simulate hitting the target
                        Command awardPoints = new AwardPointsCommand(new Target(), 100);
                        awardPoints.execute();
                        awardPoints.toString();
                        new Target().accept(pointVisitor);
                        score += 100;
                        break;
                    case 3:
                        // Simulate losing a ball
                        pinballMachine.getState().ballLost();
                        if (pinballMachine.getBallsLost() >= pinballMachine.getMaxBalls()) {
                            // If all balls are lost, transition to NoCreditState or ReadyState
                            if (pinballMachine.getCredits() >= 1) {
                                pinballMachine.setMaxBalls(3);
                                pinballMachine.setState(new ReadyState(pinballMachine));

                            } else {
                                pinballMachine.setMaxBalls(3);
                                pinballMachine.setState(new NoCreditState(pinballMachine));

                            }
                            pinballMachine.setBallsLost(0);
                            pinballMachine.decrementCredits();
                            System.out.println("Enter your name: ");
                            String playerName = scanner.nextLine();
                            leaderboard.put(playerName,score);
                            System.out.println("Game Over. Your score is: " + score);
                            isGameRunning = false;
                            score = 0;
                        }
                        break;
                    case 4:
                        isGameRunning = false;
                        break;
                    default:
                        System.out.println("Invalid action. Please try again.");
                        break;
                }
            } else {
                System.out.println("Choose an action:");
                System.out.println("1. Insert coin");
                System.out.println("2. Press start button");
                System.out.println("3. Quit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        pinballMachine.insertCoin();
                        break;
                    case 2:
                        if (pinballMachine.getState() instanceof NoCreditState) {
                            System.out.println("No credit available. Please insert a coin.");
                        } else if (pinballMachine.getState() instanceof ReadyState) {
                            pinballMachine.getState().pressStartButton();
                            System.out.println("Game started!");
                            isGameRunning = true;
                        } else {
                            System.out.println("The game is already in progress.");
                        }
                        break;
                    case 3:
                        isRunning = false;
                        List<Map.Entry<String, Integer>> sortedLeaderboard = new ArrayList<>(leaderboard.entrySet());
                        sortedLeaderboard.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

                        System.out.println("Leaderboard:");
                        for (Map.Entry<String, Integer> entry : sortedLeaderboard) {
                            System.out.println(entry.getKey() + ": " + entry.getValue());
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }

        // Close the scanner when done
        scanner.close();
    }

}
