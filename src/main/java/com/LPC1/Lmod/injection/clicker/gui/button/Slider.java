package com.LPC1.Lmod.injection.clicker.gui.button;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import java.awt.*;

import static com.LPC1.Lmod.injection.clicker.gui.utils.ColorUtils.getRGB_YODA;
import static com.LPC1.Lmod.injection.clicker.gui.utils.DrawUtils.drawCircleFilled;
import static com.LPC1.Lmod.injection.clicker.gui.utils.DrawUtils.drawRoundedThinRectangle;

public class Slider extends GuiButton {

    private final int minValue;
    private int currentProgress;
    public static int currentValue;
    private final int iterations;
    public double guiScale;
    private boolean dragging;
    private double DividableConstant;

    public Slider(int buttonId, int x, int y, int width, int height, String buttonText, int minValue, int iterations, double guiScale, double DividableConstant) {
        super(buttonId, x, y, width, height, buttonText);
        this.minValue = minValue;
        this.iterations = iterations;
        this.guiScale = guiScale;
        this.DividableConstant = DividableConstant;
    }

    @Override
    public void drawButton(Minecraft minecraft, int mouseX, int mouseY) {
        mouseX /= guiScale;
        mouseY /= guiScale;
        hovered = mouseX >= xPosition && mouseY >= yPosition && mouseX < xPosition + width && mouseY < yPosition + height;
        mouseDragged(minecraft, mouseX, mouseY);
        drawRoundedThinRectangle(xPosition, yPosition, width, height, iterations, Color.WHITE.getRGB());
        drawCircleFilled(xPosition + currentProgress + 0.5, yPosition + height / 2.0, 2, 200, getRGB_YODA(255));
        currentValue = (int) ((currentProgress + minValue) / DividableConstant);
        displayString = String.valueOf(currentProgress + minValue);
    }

    @Override
    public boolean mousePressed(Minecraft minecraft, int mouseX, int mouseY) {
        mouseX /= guiScale;
        if (hovered) {
            currentProgress = (mouseX - xPosition);
            dragging = true;
            return true;
        } else return false;
    }

    @Override
    protected void mouseDragged(Minecraft minecraft, int mouseX, int mouseY) {
        if (visible && dragging && hovered) currentProgress = (mouseX - xPosition);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY) {
        dragging = false;
    }
}