package org.dinosat;

public interface Visitor {
    void visit(Ramp ramp);
    void visit(Target target);
}
