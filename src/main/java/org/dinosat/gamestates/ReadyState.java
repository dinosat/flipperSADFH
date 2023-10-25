package org.dinosat.gamestates;

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
        System.out.println("\n" +
                " $$$$$$\\    $$\\                          $$\\     $$\\                             $$\\     $$\\                                                                               \n" +
                "$$  __$$\\   $$ |                         $$ |    \\__|                            $$ |    $$ |                                                                              \n" +
                "$$ /  \\__|$$$$$$\\    $$$$$$\\   $$$$$$\\ $$$$$$\\   $$\\ $$$$$$$\\   $$$$$$\\        $$$$$$\\   $$$$$$$\\   $$$$$$\\         $$$$$$\\   $$$$$$\\  $$$$$$\\$$$$\\   $$$$$$\\              \n" +
                "\\$$$$$$\\  \\_$$  _|   \\____$$\\ $$  __$$\\\\_$$  _|  $$ |$$  __$$\\ $$  __$$\\       \\_$$  _|  $$  __$$\\ $$  __$$\\       $$  __$$\\  \\____$$\\ $$  _$$  _$$\\ $$  __$$\\             \n" +
                " \\____$$\\   $$ |     $$$$$$$ |$$ |  \\__| $$ |    $$ |$$ |  $$ |$$ /  $$ |        $$ |    $$ |  $$ |$$$$$$$$ |      $$ /  $$ | $$$$$$$ |$$ / $$ / $$ |$$$$$$$$ |            \n" +
                "$$\\   $$ |  $$ |$$\\ $$  __$$ |$$ |       $$ |$$\\ $$ |$$ |  $$ |$$ |  $$ |        $$ |$$\\ $$ |  $$ |$$   ____|      $$ |  $$ |$$  __$$ |$$ | $$ | $$ |$$   ____|            \n" +
                "\\$$$$$$  |  \\$$$$  |\\$$$$$$$ |$$ |       \\$$$$  |$$ |$$ |  $$ |\\$$$$$$$ |        \\$$$$  |$$ |  $$ |\\$$$$$$$\\       \\$$$$$$$ |\\$$$$$$$ |$$ | $$ | $$ |\\$$$$$$$\\ $$\\ $$\\ $$\\ \n" +
                " \\______/    \\____/  \\_______|\\__|        \\____/ \\__|\\__|  \\__| \\____$$ |         \\____/ \\__|  \\__| \\_______|       \\____$$ | \\_______|\\__| \\__| \\__| \\_______|\\__|\\__|\\__|\n" +
                "                                                               $$\\   $$ |                                          $$\\   $$ |                                              \n" +
                "                                                               \\$$$$$$  |                                          \\$$$$$$  |                                              \n" +
                "                                                                \\______/                                            \\______/                                               \n");
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
