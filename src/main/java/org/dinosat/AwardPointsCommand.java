package org.dinosat;

public class AwardPointsCommand implements Command {
    private final PinballElement element;
    private final int points;
    public AwardPointsCommand(PinballElement element, int points) {
        this.element = element;
        this.points = points;
    }

    @Override
    public void execute() {
        System.out.println("Awarding " + points + " points via command");
        element.accept(new PointVisitor(points));
        element.hit();
    }
}
