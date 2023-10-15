package org.dinosat;

public class EndState implements PinballMachineState {
    private final PinballMachine pinballMachine;

    public EndState(PinballMachine pinballMachine) {
        this.pinballMachine = pinballMachine;
    }

    @Override
    public void insertCoin() {
        if (pinballMachine.getState() instanceof NoCreditState) {
            // If in NoCreditState, insert a coin to transition to ReadyState
            pinballMachine.insertCoin();
            System.out.println("You have inserted a coin. Game ready.");
        } else {
            System.out.println("No credit available. Game over.");
        }
    }

    @Override
    public void pressStartButton() {
        if (pinballMachine.getState() instanceof NoCreditState) {
            // If in NoCreditState, press start to transition to ReadyState
            pinballMachine.getState().pressStartButton();
            System.out.println("Game started!");
        } else {
            System.out.println("No credit available. Game over.");
        }
    }


    @Override
    public void ballLost() {
        System.out.println("Game over. No more balls left.");
    }

    @Override
    public String toString() {
        return "End State";
    }
}
