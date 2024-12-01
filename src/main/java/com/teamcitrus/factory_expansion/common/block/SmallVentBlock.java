package com.teamcitrus.factory_expansion.common.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IPreviewBlock;
import com.teamcitrus.factory_expansion.core.properties.FEProperties;
import com.teamcitrus.factory_expansion.core.properties.properties.MediumVentBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SmallVentBlock extends RotatedPillarBlock implements IPreviewBlock {

    public SmallVentBlock(Properties properties) {
        super(properties.noOcclusion().pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(AXIS, Direction.Axis.Y)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }

    @Override
    protected VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    protected float getShadeBrightness(BlockState p_308911_, BlockGetter p_308952_, BlockPos p_308918_) {
        return 1.0F;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState p_309084_, BlockGetter p_309133_, BlockPos p_309097_) {
        return true;
    }

    @Override
    public void renderPreview(BlockHitResult hitResult, BlockPlaceContext context, Block block, Minecraft minecraft, PoseStack stack) {

        BlockState placement = block.getStateForPlacement(context);
        if(placement == null) return;

        if(Minecraft.getInstance().level instanceof BlockAndTintGetter tint) {

            ResourceLocation texture = ResourceLocation.parse("minecraft:textures/block/white_concrete.png");

            Minecraft.getInstance().getBlockRenderer().renderBatched(
                    placement,
                    context.getClickedPos(),
                    tint,
                    stack,
                    Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.crumbling(texture)),
                    true,
                    Minecraft.getInstance().level.getRandom()
            );
        }
    }
}
