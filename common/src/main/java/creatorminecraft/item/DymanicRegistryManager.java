package creatorminecraft.item;

import creatorminecraft.screen.DynamicRegistryManagerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class DymanicRegistryManager extends Item {
//Not Functional
    public DymanicRegistryManager(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (!world.isClientSide()) {
            openAddonPluginManagerScreen(Minecraft.getInstance());

        }
        return super.use(world, user, hand);
    }

    public static void openAddonPluginManagerScreen(Minecraft minecraftClient) {
        minecraftClient.execute(() -> {
            if (!(minecraftClient.screen instanceof DynamicRegistryManagerScreen)) {
                minecraftClient.setScreen(new DynamicRegistryManagerScreen(Component.empty()));
            }
        });
    }
}
