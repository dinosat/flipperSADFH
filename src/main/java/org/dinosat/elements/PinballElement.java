package org.dinosat.elements;

import org.dinosat.visitors.Visitor;

public interface PinballElement {
    void hit();
    void accept(Visitor visitor);
}
