package org.dinosat;

public class ReadyState implements PinballMachineState {
    private final PinballMachine pinballMachine;

    public ReadyState(PinballMachine pinballMachine) {
        this.pinballMachine = pinballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("You inserted a coin. Credit increased.");
        pinballMachine.incrementCredits();
    }

    @Override
    public void pressStartButton() {
        System.out.println("Starting the game...");
        pinballMachine.setState(new PlayingState(pinballMachine));
    }

    @Override
    public void ballLost() {
        System.out.println("Ball lost. Remaining balls: " + (pinballMachine.getMaxBalls() - pinballMachine.getBallsLost()));
        if (pinballMachine.getBallsLost() >= pinballMachine.getMaxBalls()) {
            pinballMachine.setState(new EndState(pinballMachine));
        }
    }

    @Override
    public String toString() {
        return "Ready State";
    }
}
