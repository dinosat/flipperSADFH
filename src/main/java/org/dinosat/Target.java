package org.dinosat;

public class Target implements PinballElement {

    @Override
    public void hit() {
            System.out.println("Target hit!");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
