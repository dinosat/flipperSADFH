package org.dinosat;

import static org.dinosat.Main.currentScore;

public class PointVisitor implements Visitor {
    private int points;

    public PointVisitor(int points) {
        this.points = points;
    }

    @Override
    public void visit(Ramp ramp) {

        System.out.println("Calculating points for hitting a ramp.");

    }

    @Override
    public void visit(Target target) {
        System.out.println("Calculating points for hitting a target.");
         currentScore +=points;
    }
}
