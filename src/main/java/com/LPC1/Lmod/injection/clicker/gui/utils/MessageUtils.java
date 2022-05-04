package com.LPC1.Lmod.injection.clicker.gui.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;

public class MessageUtils {
    public static void sendClientChatMessage(String message, boolean Tag) {
        if (Tag) {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("[LMOD]: " + message));
        } else {
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(message));
        }
    }
}
