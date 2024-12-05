package com.teamcitrus.factory_expansion.common.block.interfaces.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public interface IPreviewBlock {

    void renderPreview(BlockPlaceContext context, BlockHitResult hitResult, BlockItem blockItem, BlockState state, PoseStack stack);
}
