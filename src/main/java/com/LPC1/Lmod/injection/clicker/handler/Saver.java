package com.LPC1.Lmod.injection.clicker.handler;

import com.LPC1.Lmod.injection.clicker.gui.MainGui;

public class Saver {
    private static MainGui lastScreen;

    public static MainGui getLastScreen() {
        return lastScreen;
    }

    public static void setLastScreen(MainGui lastScreen) {
        Saver.lastScreen = lastScreen;
    }

}
