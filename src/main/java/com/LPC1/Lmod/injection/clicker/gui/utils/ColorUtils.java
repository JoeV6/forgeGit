package com.LPC1.Lmod.injection.clicker.gui.utils;

public class ColorUtils {

    private static int red = 30;
    private static int green = 30;
    private static int blue = 200;

    public static void refreshColors() {
        if (red == 30 && green < 50 && blue == 255) green++;
        if (red < 50 && green == 50 && blue == 255) red++;
        if (red == 30 && green == 30 && blue < 255) blue++;

        if (red == 50 && green == 50 && blue > 200) blue--;
        if (red == 50 && green > 30 && blue == 200) green--;
        if (red > 30 && green == 30 && blue == 200) red--;
    }

    public static int getRed() {
        return red;
    }

    public static int getGreen() {
        return green;
    }

    public static int getBlue() {
        return blue;
    }

    public static int getRGB(int opacity) {
        return ((opacity & 0xFF) << 24) | ((red & 0xFF) << 16) | ((green & 0xFF) << 8)  | ((blue & 0xFF));
    }

    public static int getCustomRGB(int opacity, int r, int g, int b) {
        return ((opacity & 0xFF) << 24) | ((r & 0xFF) << 16) | ((g & 0xFF) << 8)  | ((b & 0xFF));
    }

    public static int getRGB_YODA(int opacity) {
        return ((opacity & 0xFF) << 24) | ((20 & 0xFF) << 16) | ((55 & 0xFF) << 8)  | ((190 & 0xFF));
    }

}
