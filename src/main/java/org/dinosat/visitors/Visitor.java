package org.dinosat.visitors;

import org.dinosat.elements.Ramp;
import org.dinosat.elements.Target;

public interface Visitor {
    void visit(Ramp ramp);
    void visit(Target target);
}
