package org.dinosat.display;

public class PinballDisplay implements Display {
    private String fontStyle;

    public PinballDisplay(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    @Override
    public void showText(String text) {
        System.out.println("Using font style: " + fontStyle);
        System.out.println(text);
    }
}
