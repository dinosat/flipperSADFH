package org.dinosat;

public class PlayingState implements PinballMachineState {
    private final PinballMachine pinballMachine;

    public PlayingState(PinballMachine pinballMachine) {
        this.pinballMachine = pinballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("You can't insert a coin while the game is in progress.");
    }

    @Override
    public void pressStartButton() {
        System.out.println("The game is already in progress.");
    }

    @Override
    public void ballLost() {
        pinballMachine.incrementBallsLost();
        System.out.println("Ball lost. Remaining balls: " + (pinballMachine.getMaxBalls() - pinballMachine.getBallsLost()));

        if (pinballMachine.getBallsLost() >= pinballMachine.getMaxBalls()) {
            // Check if all balls are lost; if so, transition to ReadyState or NoCreditState
            if (pinballMachine.getMaxBalls() > 0) {
                pinballMachine.setState(new ReadyState(pinballMachine));
            } else {
                pinballMachine.setState(new NoCreditState(pinballMachine));
            }
        }
    }
        @Override
        public String toString () {
            return "Playing State";
        }

}