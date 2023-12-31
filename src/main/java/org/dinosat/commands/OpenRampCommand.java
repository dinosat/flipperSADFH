package org.dinosat.commands;

import org.dinosat.elements.PinballElement;

public class OpenRampCommand implements Command {
    private final PinballElement element;

    public OpenRampCommand(PinballElement element) {
    this.element = element;
    }

    @Override
    public void execute() {
        System.out.println("Opening ramp via command");
        element.hit();
    }
}
