package com.LPC1.Lmod.injection.clicker.gui;

import com.LPC1.Lmod.injection.clicker.calcs.GenerateSequence;
import com.LPC1.Lmod.injection.clicker.gui.button.Slider;
import com.LPC1.Lmod.injection.clicker.gui.button.TextButton;
import com.LPC1.Lmod.injection.clicker.gui.utils.customfont.FontUtil;
import com.LPC1.Lmod.injection.setup.Setup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import static com.LPC1.Lmod.injection.clicker.gui.utils.ColorUtils.getRGB_YODA;
import static com.LPC1.Lmod.injection.clicker.gui.utils.DrawUtils.*;
import static com.LPC1.Lmod.injection.clicker.gui.utils.MessageUtils.sendClientChatMessage;

public class MainGui extends GuiScreen {

    private final ResourceLocation LogoPng = new ResourceLocation("logo_final.png");

    private static Setup setup;

    //YodaColors = 20, 55, 190;

    protected int GuiWidth = 250, GuiHeight = 125;
    protected static int GuiX;
    protected static int GuiY;
    private static double guiScale;
    private static boolean Clicked = false;

    static Minecraft minecraft = Minecraft.getMinecraft();

    private static final Slider SliderMaxCps = new Slider(1,GuiX + 40, GuiY + 55, 56, 4, "", 1, 20, guiScale, 2.7);
    private static final Slider SliderMinCps = new Slider(2,GuiX + 40, GuiY + 80, 56, 4, "", 1, 20, guiScale, 2.7);

    protected static final TextButton MainButton = new TextButton(3, 0, 0, 12, 8, "On", 1,
            () -> {
                if (minecraft.theWorld != null && minecraft.thePlayer != null) return;
                if (!Clicked) {
                    setup.getClickerOn().setOn(true);
                    setup.getFirstList().setOn(true);
                    new GenerateSequence(setup.getMaxSpeed().getValue(), setup.getMinSpeed().getValue(), 20, setup);
                    Clicked = true;
                    sendClientChatMessage("Clicked True", true);
                } else {
                    setup.getClickerOn().setOn(false);
                    Clicked = false;
                    sendClientChatMessage("Clicked False", true);
                }});

    public MainGui(Setup setup) {
        this.setup = setup;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float PartialTicks) {

        //super.drawScreen(mouseX, mouseY, PartialTicks);

        drawDefaultBackground();
        GlStateManager.scale(guiScale, guiScale, guiScale);
        drawRectangle(GuiX, GuiY, GuiWidth, GuiHeight, 140, 140, 149, 200);
        drawLine(GuiX, GuiY, 0, GuiHeight, 2, 20, 55, 190, 255);
        drawLine(GuiX, GuiY, GuiWidth, 0, 2, 20, 55, 190, 255);
        drawLine(GuiX + GuiWidth, GuiY, 0, GuiHeight, 2, 20, 55, 190, 255);
        drawLine(GuiX, GuiY + GuiHeight, GuiWidth, 0, 2, 20, 55, 190, 255);

        FontUtil.title.drawString("AutoClicker V1.0", GuiX + 40, GuiY + 15, getRGB_YODA(255));
        FontUtil.normal.drawString("Max CPS : ", GuiX + 40, GuiY + 45, getRGB_YODA(255));
        FontUtil.normal.drawString("Min CPS : ", GuiX + 40, GuiY + 70, getRGB_YODA(255));

        mc.renderEngine.bindTexture(LogoPng);
        drawTexture(GuiX + 8,GuiY + 8, 22, 22, 0,0,1,1);

        for (GuiButton button : buttonList) {
            button.drawButton(minecraft, mouseX, mouseY);
            if (button instanceof Slider) {
                FontUtil.normal.drawString(String.valueOf(Slider.currentValue), button.xPosition + 50, button.yPosition - 10, getRGB_YODA(255));
            }
        }
    }

    @Override
    public void initGui() {

        super.initGui();
        guiScale = width / 480.0;
        GuiX = (int) ((width / guiScale - GuiWidth) / 2);
        GuiY = (int) ((height / guiScale - GuiHeight) / 2);

        buttonList.clear();


        //add slider
        buttonList.add(SliderMaxCps);
        buttonList.add(SliderMinCps);
        updateSlider(SliderMaxCps, GuiX + 40, GuiY + 55);
        updateSlider(SliderMinCps, GuiX + 40, GuiY + 80);

        //add button
        buttonList.add(MainButton);
        updateTextButton(MainButton, GuiX + 150, GuiY + 15);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    protected <T extends TextButton> void updateTextButton(T button, int x1, int y1) {
        button.setGuiScale(guiScale);
        button.xPosition = x1;
        button.yPosition = y1;
    }

    protected void updateSlider(Slider slider, int x1, int y1) {
        slider.guiScale = guiScale;
        slider.xPosition = x1;
        slider.yPosition = y1;
    }
}


