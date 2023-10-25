package org.dinosat.gamestates;

public class PinballMachineAdapter implements GameSystem {
    private PinballMachine pinballMachine;

    public PinballMachineAdapter(PinballMachine pinballMachine) {
        this.pinballMachine = pinballMachine;
    }

    @Override
    public void startGame() {
        pinballMachine.insertCoin();
        pinballMachine.pressStartButton();
    }

    @Override
    public void endGame() {
        System.out.println("Game ended. All credits and balls reset.");
    }

    @Override
    public void insertCoin() {
        pinballMachine.insertCoin();
    }

    @Override
    public void pressStartButton() {
        pinballMachine.pressStartButton();
    }
}

