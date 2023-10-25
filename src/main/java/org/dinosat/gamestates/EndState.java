package org.dinosat.gamestates;

public class EndState implements PinballMachineState {
    private final PinballMachine pinballMachine;

    public EndState(PinballMachine pinballMachine) {
        this.pinballMachine = pinballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("over");
    }

    @Override
    public void pressStartButton() {
        System.out.println("over");
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
