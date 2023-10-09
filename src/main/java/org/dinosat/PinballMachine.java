package org.dinosat;

import java.util.ArrayList;
import java.util.List;

public class PinballMachine {
    private static PinballMachine instance;
    private List<PinballElement> elements = new ArrayList<>();
    private PinballMachineState state;
    private int credits = 0;
    private int ballsLost = 0;
    private int maxBalls = 3;

    // Private constructor to prevent instantiation from other classes
    private PinballMachine() {
        this.state = new NoCreditState(this);
    }

    // Public method to get the instance of the Singleton
    public static PinballMachine getInstance() {
        if (instance == null) {
            instance = new PinballMachine();
        }
        return instance;
    }

    // Add a pinball element to the machine
    public void addElement(PinballElement element) {
        elements.add(element);
    }

    // Insert a coin
    public void insertCoin() {
        state.insertCoin();
    }
    public void incrementCredits() {
        credits++;
        System.out.println("Credit increased. Total credits: " + credits);
    }
    // Press the start button
    public void pressStartButton() {
        state.pressStartButton();
    }

    // Ball is lost
    public void ballLost() {
        state.ballLost();
    }
    // Add this method to increment ballsLost
    public void incrementBallsLost() {
        ballsLost++;
    }

    // Add this method to get the number of balls lost
    public int getBallsLost() {
        return ballsLost;
    }

    // Add this method to get the maximum number of balls allowed
    public int getMaxBalls() {
        return maxBalls;
    }

    // Set the machine's state
    public void setState(PinballMachineState state) {
        this.state = state;
    }

    // Get the current state of the machine
    public String getStatus() {
        return state.toString();
    }

    // Accept a visitor to perform actions on elements
    public void acceptVisitor(Visitor visitor) {
        for (PinballElement element : elements) {
            element.accept(visitor);
        }
    }

}
