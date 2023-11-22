package creatorminecraft.gui;

import creatorminecraft.client.CreatorIGUI;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class CreatorMinecraftEditBox extends EditBox implements CreatorIGUI {
    public CreatorMinecraftEditBox() {
        super(Minecraft.getInstance().font, 0, 0, 0,  STANDARD_SIZE, Component.literal(""));
    }
}
