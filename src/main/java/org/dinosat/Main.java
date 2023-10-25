package org.dinosat;

import org.dinosat.gamestates.PinballMachine;

import java.util.HashMap;
import java.util.Map;

import static org.dinosat.gameplay.GameRunnnig.runPinballGame;


public class Main {
    public static final Map<String, Integer> leaderboard = new HashMap<>();
    public static int currentScore = 0;

    public static void main(String[] args) {
        PinballMachine pinballMachine = PinballMachine.getInstance();
        runPinballGame(pinballMachine);
    }
}
