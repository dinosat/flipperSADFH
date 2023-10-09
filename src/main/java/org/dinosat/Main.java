package org.dinosat;

public class Main {
    public static void main(String[] args) {
        PinballDisplayFactory factory1 = new FontFamily1Factory();
        PinballDisplayFactory factory2 = new FontFamily2Factory();

        Display display1 = factory1.createDisplay();
        Display display2 = factory2.createDisplay();

        display1.showText("Hello, Pinball World!");
        display2.showText("Welcome to the Pinball Adventure!");

        PinballMachine pinballMachine = PinballMachine.getInstance();
        GameSystem gameSystem = new PinballMachineAdapter(pinballMachine);

        gameSystem.startGame();
        gameSystem.insertCoin();
        gameSystem.pressStartButton();
        // Add some pinball elements
        PinballElement ramp = new Ramp();
        PinballElement target = new Target();

        // Add the pinball elements to the machine
        pinballMachine.addElement(ramp);
        pinballMachine.addElement(target);

        // Create and use command objects
        Command openRamp = new OpenRampCommand(ramp);
        Command awardPoints = new AwardPointsCommand(target, 100);

        // Create visitors
        Visitor resetVisitor = new ResetVisitor();
        Visitor pointVisitor = new PointVisitor();

        // Insert a coin and press the start button
        pinballMachine.insertCoin();
        pinballMachine.pressStartButton();

        // Simulate hitting the ramp and target
        openRamp.execute();
        awardPoints.execute();

        // Simulate a ball loss
        pinballMachine.ballLost();
        pinballMachine.ballLost();
        pinballMachine.ballLost();

        // Use the visitors to reset and calculate points
        pinballMachine.acceptVisitor(resetVisitor);
        pinballMachine.acceptVisitor(pointVisitor);

        // Display the pinball machine status
        System.out.println("Pinball Machine Status: " + pinballMachine.getStatus());
    }
}