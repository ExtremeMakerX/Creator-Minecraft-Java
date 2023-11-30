package creatorminecraft;

import creatorminecraft.item.AddonPluginManager;
import creatorminecraft.item.CreatorMinecraft;
import creatorminecraft.item.DymanicRegistryManager;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public final class Items {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(CreatorMCID.MOD_ID, Registries.ITEM);
    static {
        //ADDON_PLUGIN_MANAGER = ITEMS.register("addon_plugin_manager", () -> new AddonPluginManager(new Item.Properties()));
       // DYNAMIC_REGISTRY_MANAGER = ITEMS.register("dynamic_registry_manager", () -> new DymanicRegistryManager(new Item.Properties()));
        CREATOR_MINECRAFT_JAVA = ITEMS.register("creator_minecraft_java", () -> new CreatorMinecraft(new Item.Properties()));
        CREATOR_WORLD_VISUAL_ITEM = ITEMS.register("creator_world_visual", () -> new BlockItem(Blocks.CREATOR_WORLD_VISUAL.get(), new Item.Properties()));
    }
   // public final static RegistrySupplier<Item> DYNAMIC_REGISTRY_MANAGER;
    //public final static RegistrySupplier<Item> ADDON_PLUGIN_MANAGER;
    public final static RegistrySupplier<Item> CREATOR_MINECRAFT_JAVA;
    public final static RegistrySupplier<Item> CREATOR_WORLD_VISUAL_ITEM;

    public static void init() {
        ITEMS.register();
        CreatorMC.LOGGER.info("Registering Creator Minecraft Java items");
    }

}
