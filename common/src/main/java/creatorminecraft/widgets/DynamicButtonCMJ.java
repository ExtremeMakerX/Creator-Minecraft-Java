package creatorminecraft.widgets;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

import java.util.function.Supplier;

public class DynamicButtonCMJ extends Button {

    public static final CreateNarration DEFAULT_NARRATION = Supplier::get;

    public DynamicButtonCMJ(int i, int j, int k, int l, Component component, OnPress onPress, CreateNarration createNarration) {
        super(i, j, k, l, component, onPress, createNarration);
    }
}
