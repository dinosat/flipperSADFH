package org.dinosat.gamestates;

import org.dinosat.elements.PinballElement;

import java.util.ArrayList;
import java.util.List;

public class PinballMachine {
    private static PinballMachine instance;
    private List<PinballElement> elements = new ArrayList<>();
    private PinballMachineState state;
    private int credits = 0;
    private int ballsLost = 0;
    private  int maxBalls = 3;

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
        if(credits== 0){
            credits++;
        }
        System.out.println("Credit increased. Total credits: " + credits);
    }
    public void incrementCredits() {
        credits++;
    }

    public void decrementCredits(){
        credits--;
        System.out.println("Your Credit goes down and down (-1). Total credits: " + credits);
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

    public void setMaxBalls(int maxBalls) {
        this.maxBalls = maxBalls;
    }

    public int getCredits() {
        return credits;
    }

    public void setBallsLost(int ballsLost) {
        this.ballsLost = ballsLost;
    }

    // Set the machine's state
    public void setState(PinballMachineState state) {
        this.state = state;
    }
    public PinballMachineState getState() {
        return state;
    }
    // Get the current state of the machine
    public String getStatus() {
        return state.toString();
    }
}
