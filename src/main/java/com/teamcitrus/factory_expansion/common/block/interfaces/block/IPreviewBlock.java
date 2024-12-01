package com.teamcitrus.factory_expansion.common.block.interfaces.block;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.BlockHitResult;

public interface IPreviewBlock {

    void renderPreview(BlockHitResult hitResult, BlockPlaceContext context, Block block, Minecraft minecraft, PoseStack stack);
}
