package com.teamcitrus.factory_expansion.client.render.misc;

import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

public class MiscRendering {

    public static void renderCapacityBar(ItemStack stack, int x, int y, GuiGraphics graphics) {

        // todo

        int l = Math.round(-stack.get(FEComponents.CANISTER).used() * -13.0F / stack.get(FEComponents.CANISTER).getCapacity());
        int i = Mth.hsvToRgb(189, 88, 100);

        x += 2;
        y += 13;
        if(stack.isBarVisible()) y -= 2;

        graphics.fill(RenderType.guiOverlay(), x, y, x + 13, y + 2, 0, -16777216);
        graphics.fill(RenderType.guiOverlay(), x, y, x + l,  y + 1, 0, i | 0xFF000000); // idk how this works, the bar always appears yellow ?
    }
}
