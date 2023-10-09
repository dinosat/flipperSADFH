package org.dinosat;

public interface PinballDisplayFactory {
    String getFontStyle();  // Use a string identifier for the font style
    Display createDisplay();
}
