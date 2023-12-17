package org.creatorminecraft.mod.item;

import org.creatorminecraft.mod.screen.DynamicRegistryManagerScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;

public class DymanicRegistryManager extends ItemExtension {

    public DymanicRegistryManager(ItemSettings itemSettings) {
        super(itemSettings);
    }
//openDynamicRegistryManagerScreen

    @Override
    public void useWithoutResult(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            openDynamicRegistryManagerScreen(MinecraftClient.getInstance());
        }
    }

    public static void openDynamicRegistryManagerScreen(MinecraftClient minecraftClient) {
        minecraftClient.execute(() -> minecraftClient.setCurrentScreenMapped(new Screen(new DynamicRegistryManagerScreen())));
    }
}

