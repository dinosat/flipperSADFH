package org.dinosat;

public class EndState implements PinballMachineState {
    private final PinballMachine pinballMachine;

    public EndState(PinballMachine pinballMachine) {
        this.pinballMachine = pinballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Game over. You can't insert a coin.");
    }

    @Override
    public void pressStartButton() {
        System.out.println("Game over. You can't start a new game.");
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
