package com.teamcitrus.factory_expansion.client.render.misc;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.teamcitrus.factory_expansion.common.item.cycleable.CycleBlockItem;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;

public class CycleItemIcon implements LayeredDraw.Layer {

    public static final LayeredDraw.Layer LAYER = new CycleItemIcon();

    @Override
    public void render(GuiGraphics guiGraphics, DeltaTracker deltaTracker) {

        if(Minecraft.getInstance().player == null) return;

        if(Minecraft.getInstance().player.getMainHandItem().getItem() instanceof CycleBlockItem item) {

            ResourceLocation sprite = item.getOptStateBlock().getIcon();

            guiGraphics.blitSprite(sprite,(guiGraphics.guiWidth() / 2) - 9, 1 + 5, 17, 17);

            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.7F);

            sprite = item.getOptStateBlock(item.getNextIndex(item.getIndex())).getIcon();

            guiGraphics.blitSprite(sprite,(guiGraphics.guiWidth() / 2) - 6 + 19, 5 + 5, 11, 11);

            sprite = item.getOptStateBlock(item.getPreviousIndex(item.getIndex())).getIcon();

            guiGraphics.blitSprite(sprite,(guiGraphics.guiWidth() / 2) - 6 - 20,  5 + 5, 11, 11);

            RenderSystem.disableBlend();
        }
    }
}
