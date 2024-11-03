package com.teamcitrus.factory_expansion.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.core.registry.FEItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;

public class FlamethrowerRenderer extends BlockEntityWithoutLevelRenderer {

    public FlamethrowerRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        if(stack.is(FEItems.FLAMETHROWER)) {
            //System.out.println("rendering 2!");
            Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.ACACIA_PLANKS.defaultBlockState(), poseStack, buffer, packedLight, packedOverlay);
        }

    }
}
