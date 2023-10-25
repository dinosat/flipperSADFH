package org.dinosat.visitors;

import org.dinosat.elements.Ramp;
import org.dinosat.elements.Target;

public class ResetVisitor implements Visitor {
    @Override
    public void visit(Ramp ramp) {
        System.out.println("Resetting ramp: Closing the ramp.");

    }

    @Override
    public void visit(Target target) {
        System.out.println("Resetting target: Powering up the target.");

    }
}
