package com.teamcitrus.factory_expansion.client.render.misc;

import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import com.teamcitrus.factory_expansion.core.util.ColourUtils;
import com.teamcitrus.factory_expansion.core.util.RGBA;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;

public class MiscRendering {

    public static void renderCapacityBar(ItemStack stack, int x, int y, GuiGraphics graphics) {
        int progress = Math.round(-stack.get(FEComponents.CANISTER).used() * -13.0F / stack.get(FEComponents.CANISTER).getCapacity());

        x += 2;
        y += 13;
        if(stack.isBarVisible()) y -= 2;

        graphics.fill(RenderType.guiOverlay(), x, y, x + 13, y + 2, 0, -16777216);
        graphics.fill(RenderType.guiOverlay(), x, y, x + progress,  y + 1, 0, ColourUtils.RGBAToHex(new RGBA(0, 255, 255, 255)) | 0xFF000000);
    }


}
