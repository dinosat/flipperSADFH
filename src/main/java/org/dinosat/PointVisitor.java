package org.dinosat;

public class PointVisitor implements Visitor {
    @Override
    public void visit(Ramp ramp) {
        System.out.println("Calculating points for hitting a ramp.");

    }

    @Override
    public void visit(Target target) {
        System.out.println("Calculating points for hitting a target.");

    }
}
