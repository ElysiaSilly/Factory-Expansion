package com.elysiasilly.fne.client.render.misc;

import com.elysiasilly.babel.util.resource.RGBA;
import com.elysiasilly.fne.common.component.CanisterComponent;
import com.elysiasilly.fne.core.registry.FEComponents;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.ItemStack;

public class MiscRendering {

    public static void renderCapacityBar(ItemStack stack, int x, int y, GuiGraphics graphics) {
        CanisterComponent component = stack.get(FEComponents.CANISTER);

        int progress = Math.round(-component.uses() * -13.0F / component.capacity());

        x += 2;
        y += 13;
        if(stack.isBarVisible()) y -= 2;

        graphics.fill(RenderType.guiOverlay(), x, y, x + 13, y + 2, 0, RGBA.BLACK.abgr());
        graphics.fill(RenderType.guiOverlay(), x, y, x + progress,  y + 1, 0, component.creative() ? new RGBA(164, 17, 172).abgr() : new RGBA(55, 216, 216).abgr());
    }

}
