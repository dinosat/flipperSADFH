package org.dinosat.gameplay;

import org.dinosat.commands.AwardPointsCommand;
import org.dinosat.commands.Command;
import org.dinosat.commands.OpenRampCommand;
import org.dinosat.elements.Ramp;
import org.dinosat.elements.Target;
import org.dinosat.gamestates.NoCreditState;
import org.dinosat.gamestates.PinballMachine;
import org.dinosat.gamestates.ReadyState;
import org.dinosat.visitors.ResetVisitor;

import java.util.Scanner;

import static org.dinosat.Main.currentScore;
import static org.dinosat.Main.leaderboard;


public class Gameplay {

    public static int showMainMenuAndGetChoice(Scanner scanner) {
        System.out.println("Choose an action:");
        System.out.println("1. Insert coin");
        System.out.println("2. Press start button");
        System.out.println("3. Quit");

        return scanner.nextInt();
    }

    public static boolean startGame(PinballMachine pinballMachine, boolean isGameRunning) {
        if (pinballMachine.getState() instanceof NoCreditState) {
            System.out.println("No credit available. Please insert a coin.");
        } else if (pinballMachine.getState() instanceof ReadyState) {
            pinballMachine.getState().pressStartButton();
            System.out.println("Game started!");
            pinballMachine.decrementCredits();
            isGameRunning = true;
        } else {
            System.out.println("The game is already in progress.");
        }
        return isGameRunning;
    }

    public static boolean playGame(PinballMachine pinballMachine, Scanner scanner) {
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
        System.out.println("Enter your name: ");
        String playerName = scanner.nextLine();
        leaderboard.put(playerName, currentScore);

        System.out.println("Game Over. Your score is: " + currentScore);
        currentScore = 0;
    }

}
