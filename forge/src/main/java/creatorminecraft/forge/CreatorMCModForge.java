package creatorminecraft.forge;

import creatorminecraft.CreatorMC;
import dev.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CreatorMC.MOD_ID)
public class CreatorMCModForge {
    public CreatorMCModForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(CreatorMC.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        CreatorMC.init();
    }
}
