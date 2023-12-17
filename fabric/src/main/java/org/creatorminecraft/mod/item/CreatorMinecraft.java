package org.creatorminecraft.mod.item;

import org.creatorminecraft.mod.screen.CreatorMinecraftScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;

public class CreatorMinecraft extends ItemExtension {


    public CreatorMinecraft(ItemSettings itemSettings) {
        super(itemSettings);
    }

    @Override
    public void useWithoutResult(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            openCreatorMinecraftScreen(MinecraftClient.getInstance());
        }
    }

    public static void openCreatorMinecraftScreen(MinecraftClient minecraftClient) {
        minecraftClient.execute(() -> minecraftClient.openScreen(new Screen(new CreatorMinecraftScreen())));
    }
}

