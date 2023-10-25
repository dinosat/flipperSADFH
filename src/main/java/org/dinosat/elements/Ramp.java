package org.dinosat.elements;

import org.dinosat.visitors.Visitor;

public class Ramp implements PinballElement {
    @Override
    public void hit() {
        System.out.println("Ramp hit !");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
