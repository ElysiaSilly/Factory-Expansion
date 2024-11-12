package com.teamcitrus.factory_expansion.common.block;

import com.teamcitrus.factory_expansion.core.properties.FEProperties;
import com.teamcitrus.factory_expansion.core.properties.properties.LargeVentBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.PushReaction;
import org.jetbrains.annotations.Nullable;

public class LargeVentBlock extends RotatedPillarBlock {

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
                    case X -> adjusted = adjusted.offset(0, fan.getRelativePos().getZ(), fan.getRelativePos().getX());
                    case Y -> adjusted = adjusted.offset(fan.getRelativePos().getX(), 0, fan.getRelativePos().getZ());
                    case Z -> adjusted = adjusted.offset(fan.getRelativePos().getX(), fan.getRelativePos().getZ(), 0);
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
                    case X -> adjusted = adjusted.offset(0, fan.getRelativePos().getZ(), fan.getRelativePos().getX());
                    case Y -> adjusted = adjusted.offset(fan.getRelativePos().getX(), 0, fan.getRelativePos().getZ());
                    case Z -> adjusted = adjusted.offset(fan.getRelativePos().getX(), fan.getRelativePos().getZ(), 0);
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
                        case X -> adjusted = adjusted.offset(0, fan.getRelativePos().getZ(), fan.getRelativePos().getX());
                        case Y -> adjusted = adjusted.offset(fan.getRelativePos().getX(), 0, fan.getRelativePos().getZ());
                        case Z -> adjusted = adjusted.offset(fan.getRelativePos().getX(), fan.getRelativePos().getZ(), 0);
                    }

                    if(level.getBlockState(pos.offset(adjusted)).is(this)) level.destroyBlock(pos.offset(adjusted), false);
                }
            }
        } else {
            BlockPos adjusted = new BlockPos(0, 0, 0);

            switch(axis) {
                case X -> adjusted = adjusted.offset(0, block.getRelativePos().getZ() * -1, block.getRelativePos().getX() * -1);
                case Y -> adjusted = adjusted.offset(block.getRelativePos().getX() * -1, 0, block.getRelativePos().getZ() * -1);
                case Z -> adjusted = adjusted.offset(block.getRelativePos().getX() * -1, block.getRelativePos().getZ() * -1, 0);
            }

            if(level.getBlockState(pos.offset(adjusted)).is(this)) level.destroyBlock(pos.offset(adjusted), false);
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        super.tick(state, level, pos, random);
    }
}
