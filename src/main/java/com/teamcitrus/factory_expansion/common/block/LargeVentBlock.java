package com.teamcitrus.factory_expansion.common.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.client.render.misc.BlockPreviewRenderer;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IPreviewBlock;
import com.teamcitrus.factory_expansion.core.properties.FEProperties;
import com.teamcitrus.factory_expansion.core.properties.properties.LargeVentBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
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

public class LargeVentBlock extends RotatedPillarBlock implements IPreviewBlock {

    public static final EnumProperty<LargeVentBlocks> POS = FEProperties.LARGE_VENT_BLOCKS;

    public LargeVentBlock(Properties properties) {
        super(properties.noOcclusion().pushReaction(PushReaction.DESTROY));
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(AXIS, Direction.Axis.Y)
                .setValue(POS, LargeVentBlocks.MIDDLE)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS, POS);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();

        Direction.Axis axis = context.getClickedFace().getAxis();
        boolean flag = true;

        for(LargeVentBlocks fan : LargeVentBlocks.values()) {
            if(fan != LargeVentBlocks.MIDDLE) {
                BlockPos adjusted = new BlockPos(0, 0, 0);

                switch(axis) {
                    case X -> adjusted = adjusted.offset(0, fan.position().getZ(), fan.position().getX());
                    case Y -> adjusted = adjusted.offset(fan.position().getX(), 0, fan.position().getZ());
                    case Z -> adjusted = adjusted.offset(fan.position().getX(), fan.position().getZ(), 0);
                }

                if(!level.getBlockState(pos.offset(adjusted)).canBeReplaced()) {
                    flag = false;
                    break;
                }
            }
        }

        return flag ? super.getStateForPlacement(context) : null;
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {

        Direction.Axis axis = state.getValue(AXIS);

        for(LargeVentBlocks fan : LargeVentBlocks.values()) {
            if(fan != LargeVentBlocks.MIDDLE) {
                BlockPos adjusted = new BlockPos(0, 0, 0);

                switch(axis) {
                    case X -> adjusted = adjusted.offset(0, fan.position().getZ(), fan.position().getX());
                    case Y -> adjusted = adjusted.offset(fan.position().getX(), 0, fan.position().getZ());
                    case Z -> adjusted = adjusted.offset(fan.position().getX(), fan.position().getZ(), 0);
                }

                level.setBlock(pos.offset(adjusted), this.defaultBlockState().setValue(POS, fan).setValue(AXIS, axis), 3);
            }
        }
    }

    @Override
    protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {

        LargeVentBlocks block = state.getValue(POS);
        Direction.Axis axis = state.getValue(AXIS);

        if(block == LargeVentBlocks.MIDDLE) {
            for(LargeVentBlocks fan : LargeVentBlocks.values()) {
                if(fan != LargeVentBlocks.MIDDLE) {
                    BlockPos adjusted = new BlockPos(0, 0, 0);

                    switch(axis) {
                        case X -> adjusted = adjusted.offset(0, fan.position().getZ(), fan.position().getX());
                        case Y -> adjusted = adjusted.offset(fan.position().getX(), 0, fan.position().getZ());
                        case Z -> adjusted = adjusted.offset(fan.position().getX(), fan.position().getZ(), 0);
                    }

                    if(level.getBlockState(pos.offset(adjusted)).is(this)) level.destroyBlock(pos.offset(adjusted), false);
                }
            }
        } else {
            BlockPos adjusted = new BlockPos(0, 0, 0);

            switch(axis) {
                case X -> adjusted = adjusted.offset(0, block.position().getZ() * -1, block.position().getX() * -1);
                case Y -> adjusted = adjusted.offset(block.position().getX() * -1, 0, block.position().getZ() * -1);
                case Z -> adjusted = adjusted.offset(block.position().getX() * -1, block.position().getZ() * -1, 0);
            }

            if(level.getBlockState(pos.offset(adjusted)).is(this)) level.destroyBlock(pos.offset(adjusted), false);
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
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
    protected float getShadeBrightness(BlockState state, BlockGetter getter, BlockPos pos) {
        return 1.0F;
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }

    @Override
    public void renderPreview(BlockPlaceContext context, BlockHitResult hitResult, BlockItem blockItem, BlockState state, PoseStack stack) {

        if(context.getItemInHand().getCount() < 9 && !context.getPlayer().hasInfiniteMaterials()) return;

        BlockPreviewRenderer.renderGhostBlock(state, stack, context);
    }
}
