package org.creatorminecraft.mod;


import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.ItemConvertible;
import org.mtr.mapping.holder.ItemStack;
import org.mtr.mapping.registry.CreativeModeTabHolder;
import org.mtr.mapping.registry.Registry;

public class ItemGroups {

    static {
        CREATOR_ESSENTIALS_TAB = Registry.createCreativeModeTabHolder(new Identifier(Init.MOD_ID, "core"), () -> new ItemStack(new ItemConvertible(Blocks.CREATOR_WORLD_VISUAL.get().data)));
    }

    public final static CreativeModeTabHolder CREATOR_ESSENTIALS_TAB;

    public static void init() {

        CreatorMC.LOGGER.info("Registering Creator Minecraft Java ItemGroups");
    }

}
