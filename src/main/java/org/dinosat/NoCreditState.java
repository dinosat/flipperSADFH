package org.dinosat;


public class NoCreditState implements PinballMachineState {
    private final PinballMachine pinballMachine;

    public NoCreditState(PinballMachine pinballMachine) {
        this.pinballMachine = pinballMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("You inserted a coin. Credit added.");
        pinballMachine.setState(new ReadyState(pinballMachine));
    }

    @Override
    public void pressStartButton() {
        System.out.println("No credit available. Please insert a coin.");

    }

    @Override
    public void ballLost() {
        System.out.println("No credit available. Please insert a coin.");

    }
    @Override
    public String toString() {
        return "No Credit State";
    }
}
