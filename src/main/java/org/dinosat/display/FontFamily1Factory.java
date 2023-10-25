package org.dinosat.display;

public class FontFamily1Factory implements PinballDisplayFactory {

    @Override
    public String getFontStyle() {
        return "FontFamily1";  // Use a string identifier for the font style
    }

    @Override
    public Display createDisplay() {
        return new PinballDisplay(getFontStyle());
    }
}
