package com.teamcitrus.factory_expansion.common.block;

import com.teamcitrus.factory_expansion.common.item.cycleable.CycleBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.Nullable;

public class CatwalkBlock extends SlabBlock {

    public static final BooleanProperty NORTH = BooleanProperty.create("north");
    public static final BooleanProperty EAST = BooleanProperty.create("east");
    public static final BooleanProperty SOUTH = BooleanProperty.create("west");
    public static final BooleanProperty WEST = BooleanProperty.create("south");
    public static final BooleanProperty UP = BooleanProperty.create("up");
    public static final BooleanProperty DOWN = BooleanProperty.create("down");


    public CatwalkBlock(Properties properties) {
        super(properties.noOcclusion());
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(NORTH, false)
                .setValue(EAST, false)
                .setValue(SOUTH, false)
                .setValue(WEST, false)
                .setValue(UP, false)
                .setValue(DOWN, false)
                .setValue(WATERLOGGED, false)
                .setValue(TYPE, SlabType.BOTTOM)
        );
    }

    @Override
    protected boolean skipRendering(BlockState state, BlockState adjacentBlockState, Direction side) {
        return false;// adjacentBlockState.is(this) || super.skipRendering(state, adjacentBlockState, side);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockState state = this.defaultBlockState();
        BlockPos pos = context.getClickedPos();

        //SlabType slabType = this.defaultBlockState().getValue(TYPE);

        //if(context.getItemInHand().getItem() instanceof CycleBlockItem item) {
        //    SlabType temp = (SlabType) item.getOptStateBlock().getProperty(TYPE);
        //    if(temp != null) slabType = temp;
        //}

        boolean flag = level.getBlockState(pos.above()).is(this) ? level.getBlockState(pos.above()).getValue(TYPE) != SlabType.TOP : false;
        state = state.setValue(UP, flag);

        //if(slabType != SlabType.DOUBLE) {
        //    System.out.println(slabType);

            if (level.getBlockState(pos).is(this)) {
                state = state.setValue(TYPE, SlabType.DOUBLE).setValue(WATERLOGGED, level.getBlockState(pos).getValue(WATERLOGGED));
            } else {
                state = state.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
                SlabType slab = (context.getClickLocation().y - pos.getY()) > 0.5 ? SlabType.TOP : SlabType.BOTTOM;
                state = state.setValue(TYPE, slab);
            }
            return state;
        //} else {
        //    return state;
        //}
    }

    @Override
    protected boolean canBeReplaced(BlockState state, BlockPlaceContext useContext) {

        if(useContext.getItemInHand().getItem() instanceof CycleBlockItem item) {
            SlabType temp = (SlabType) item.getOptStateBlock().getProperty(TYPE);
            if(temp == SlabType.DOUBLE) return false;
        };

        return super.canBeReplaced(state, useContext);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {

        if(level.isClientSide()) return state;

        boolean flag = facingState.is(this); //? state.getValue(TYPE) == SlabType.DOUBLE && (facingState.getValue(TYPE) == SlabType.DOUBLE || facingState.getValue(TYPE) == SlabType.BOTTOM) : false;

        switch(facing) {
            case UP -> state = state.setValue(UP, flag);
            case SOUTH -> state = state.setValue(WEST, flag); // ??
            case EAST -> state = state.setValue(EAST, flag);
            case NORTH -> state = state.setValue(NORTH, flag);
            case WEST -> state = state.setValue(SOUTH, flag); // ??
            case DOWN -> state = state.setValue(DOWN, flag);
        }

        return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, WATERLOGGED, NORTH, EAST, SOUTH, WEST, UP, DOWN);
    }
}
