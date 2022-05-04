package com.LPC1.Lmod.injection.clicker.handler;

public class ConstructorSaver {

    private boolean clicked = false;
    private boolean on = false;
    private int value;

    public boolean isClicked() {
        return this.clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public boolean isOn() {
        return this.on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}

