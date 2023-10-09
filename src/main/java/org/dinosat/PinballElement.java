package org.dinosat;

public interface PinballElement {
    void hit();
    void accept(Visitor visitor);
}
