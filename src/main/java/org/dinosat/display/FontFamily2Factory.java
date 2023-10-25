package org.dinosat.display;

public class FontFamily2Factory implements PinballDisplayFactory {
    @Override
    public String getFontStyle() {
        return "FontFamily2";
    }

    @Override
    public Display createDisplay() {
        return new PinballDisplay(getFontStyle());
    }
}
