package org.creatorminecraft.mod.gui;

import org.creatorminecraft.mod.client.CreatorIGUI;
import org.mtr.mapping.mapper.TextFieldWidgetExtension;
import org.mtr.mapping.tool.TextCase;


public class CreatorMinecraftEditBox extends TextFieldWidgetExtension implements CreatorIGUI {
    public CreatorMinecraftEditBox(int x, int y, int width, int height, int maxLength, TextCase textCase, String filter, String suggestion) {
        super(x, y, width, height, "", maxLength, textCase, filter, suggestion);
    }
}
