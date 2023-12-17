package org.creatorminecraft.mod;

import org.creatorminecraft.mod.item.CreatorMinecraft;
import org.creatorminecraft.mod.item.CreatorWorldVisualItem;
import org.mtr.mapping.holder.Identifier;
import org.mtr.mapping.holder.Item;
import org.mtr.mapping.registry.ItemRegistryObject;
import org.mtr.mapping.registry.Registry;

public final class Items {

    static {
        //ADDON_PLUGIN_MANAGER = ITEMS.register("addon_plugin_manager", () -> new AddonPluginManager(new Item.Properties()));
        //DYNAMIC_REGISTRY_MANAGER = ITEMS.register("dynamic_registry_manager", () -> new DymanicRegistryManager(new Item.Properties()));
        CREATOR_MINECRAFT_JAVA = Registry.registerItem(new Identifier(Init.MOD_ID,  "creator_minecraft_java"), itemSettings -> new Item(new CreatorMinecraft(itemSettings)), ItemGroups.CREATOR_ESSENTIALS_TAB);
        //CREATOR_WORLD_VISUAL_ITEM = Registry.registerItem(new Identifier(Init.MOD_ID, "creator_world_visual"), itemSettings -> new Item(new CreatorWorldVisualItem(itemSettings)), ItemGroups.CREATOR_ESSENTIALS_TAB);
    }

    //public final static ItemRegistryObject DYNAMIC_REGISTRY_MANAGER;
    //public final static ItemRegistryObject ADDON_PLUGIN_MANAGER;
    public final static ItemRegistryObject CREATOR_MINECRAFT_JAVA;
    //public final static ItemRegistryObject CREATOR_WORLD_VISUAL_ITEM;

    public static void init() {

        CreatorMC.LOGGER.info("Registering Creator Minecraft Java items");
    }

}
