package creatorminecraft;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ItemGroups {

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(CreatorMCID.MOD_ID, Registries.CREATIVE_MODE_TAB);

    static {
        CREATOR_ESSENTIALS_TAB = TABS.register("creator_essentials_tab", () -> CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0).icon(() -> new ItemStack(Items.CREATOR_MINECRAFT_JAVA.get())).title(Component.translatable("itemgroup." + CreatorMCID.MOD_ID + ".creator_essentials")).displayItems((features, output) -> {
            output.accept(new ItemStack(Items.CREATOR_MINECRAFT_JAVA.get()));
            //output.accept(new ItemStack(Items.ADDON_PLUGIN_MANAGER.get()));
            //output.accept(new ItemStack(Items.DYNAMIC_REGISTRY_MANAGER.get()));
            output.accept(new ItemStack(Items.CREATOR_WORLD_VISUAL_ITEM.get()));
        }).build());
    }
    public final static RegistrySupplier<CreativeModeTab> CREATOR_ESSENTIALS_TAB;

    public static void init() {
        TABS.register();
        CreatorMC.LOGGER.info("Registering Creator Minecraft Java ItemGroups");
    }

}
