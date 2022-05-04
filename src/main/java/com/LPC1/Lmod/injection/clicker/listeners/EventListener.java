package com.LPC1.Lmod.injection.clicker.listeners;

import com.LPC1.Lmod.injection.clicker.calcs.GenerateSequence;
import com.LPC1.Lmod.injection.clicker.gui.MainGui;
import com.LPC1.Lmod.injection.setup.Setup;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import java.awt.*;
import java.util.ArrayList;

import static com.LPC1.Lmod.injection.clicker.gui.utils.ColorUtils.refreshColors;
import static com.LPC1.Lmod.injection.clicker.gui.utils.MessageUtils.sendClientChatMessage;


public class EventListener {

    public static ArrayList<Integer> ClickList = new ArrayList<Integer>();

    final Robot bot = new Robot();

    private int Ticks = 0;

    private Setup setup;

    Minecraft mc = Minecraft.getMinecraft();
    private int Clicks = 0;

    private final MainGui mainGui = new MainGui(setup);

    public EventListener(Setup setup) throws AWTException {this.setup = setup;}




    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {

        setup.getClickerOn().setOn(false);
        setup.getListGenerated().setOn(false);
        setup.getFirstList().setOn(true);

        setup.getClickCount().setValue(0);
        setup.getTemp().setValue(0);

        setup.getMaxSpeed().setValue(0);
        setup.getMinSpeed().setValue(0);

        setup.getMaxSpeedSet().setOn(true);
        setup.getMinSpeedSet().setOn(true);

    }

    @SubscribeEvent
    public void OnMouseEvent(MouseEvent event) {
        if (event.button == 0) Clicks++;
    }


    @SubscribeEvent
    public void OnClientTickEvent(TickEvent.ClientTickEvent event) {

        if (mc.thePlayer == null || mc.theWorld == null) { return; }

        refreshColors();

        if (Ticks == 20) {

            Ticks = 0;

            if (setup.getClickerOn().isOn()) {
                new GenerateSequence(setup.getMaxSpeed().getValue(), setup.getMinSpeed().getValue(), 20, setup);
            }
            if (setup.getCpsOn().isOn() && mc.gameSettings.keyBindAttack.isKeyDown() && Mouse.isButtonDown(0)) {
                sendClientChatMessage("CPS " + Clicks / 2, true);
            }

            Clicks = 0;
        }


        if (setup.getClickerOn().isOn() && setup.getListGenerated().isOn() && ClickList.get(Ticks) == 1 && mc.gameSettings.keyBindAttack.isKeyDown() && Mouse.isButtonDown(0)) {
            MouseClick();
        }

        Ticks++;

        if (event.phase == TickEvent.Phase.END) {
            if (Keyboard.isKeyDown(Keyboard.KEY_O) && mc.currentScreen == null) {
                mc.displayGuiScreen(mainGui);
            }
        }
    }

    private void MouseClick() {

        if (mc.gameSettings.keyBindAttack.isKeyDown() && Mouse.isButtonDown(0)) {

            bot.mouseRelease(16);
            bot.mousePress(16);

        }
    }

}

