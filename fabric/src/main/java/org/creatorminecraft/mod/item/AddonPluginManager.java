package org.creatorminecraft.mod.item;

import org.creatorminecraft.mod.screen.AddonPluginManagerScreen;
import org.creatorminecraft.mod.screen.CreatorMinecraftScreen;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.ItemExtension;

public class AddonPluginManager extends ItemExtension {

    public AddonPluginManager(ItemSettings itemSettings) {
        super(itemSettings);
    }

    @Override
    public void useWithoutResult(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient()) {
            openAddonPluginManagerScreen(MinecraftClient.getInstance());
        }
    }

    public static void openAddonPluginManagerScreen(MinecraftClient minecraftClient) {
        minecraftClient.execute(() -> minecraftClient.setCurrentScreenMapped(new Screen(new AddonPluginManagerScreen())));
    }
}
