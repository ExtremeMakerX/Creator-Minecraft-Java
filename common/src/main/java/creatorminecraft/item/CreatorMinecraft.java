package creatorminecraft.item;

import creatorminecraft.screen.CreatorMinecraftScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class CreatorMinecraft extends Item {

    public CreatorMinecraft(Properties settings) {
        super(settings);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
        if (!world.isClientSide()) {
           openCreatorMinecraftScreen( Minecraft.getInstance());
        }
        return super.use(world, user, hand);
    }

    public static void openCreatorMinecraftScreen(Minecraft minecraftClient) {
        minecraftClient.execute(() -> {
            if (!(minecraftClient.screen instanceof CreatorMinecraftScreen)) {
                minecraftClient.setScreen(new CreatorMinecraftScreen());
            }
        });
    }
}
