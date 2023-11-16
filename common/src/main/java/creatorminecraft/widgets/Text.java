package creatorminecraft.widgets;

import net.minecraft.network.chat.contents.TranslatableContents;

public interface Text {
    static TranslatableContents translatable(String text, String text2, Object... objects) {
        return new TranslatableContents(text, text2, objects);
    }
}
