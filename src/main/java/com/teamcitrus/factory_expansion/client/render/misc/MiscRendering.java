package com.teamcitrus.factory_expansion.client.render.misc;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.core.registry.FEComponents;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

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

    public static void renderGhostBlock(Block block, BlockPlaceContext context, PoseStack stack) {

        BlockState placement = block.getStateForPlacement(context);
        if(placement == null) return;

        if(Minecraft.getInstance().level instanceof BlockAndTintGetter tint) {

            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.4F);

            Minecraft.getInstance().getBlockRenderer().renderBatched(
                    placement,
                    context.getClickedPos(),
                    tint,
                    stack,
                    Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.translucent()),
                    true,
                    Minecraft.getInstance().level.getRandom()
            );

            RenderSystem.disableBlend();
        }
    }
}
