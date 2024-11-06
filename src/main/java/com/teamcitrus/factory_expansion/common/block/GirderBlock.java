package com.teamcitrus.factory_expansion.common.block;

import com.teamcitrus.factory_expansion.common.block.interfaces.IWrenchableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Objects;

public class GirderBlock extends Block implements SimpleWaterloggedBlock, IWrenchableBlock {

    private static final VoxelShape SHAPE_X = Block.box(0, 0, 4, 16, 16, 12);
    private static final VoxelShape SHAPE_Y = Block.box(4, 0, 4, 12, 16, 12);
    private static final VoxelShape SHAPE_Z = Block.box(4, 0, 0, 12, 16, 16);

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final BooleanProperty X_AXIS = BooleanProperty.create("x");
    public static final BooleanProperty Y_AXIS = BooleanProperty.create("y");
    public static final BooleanProperty Z_AXIS = BooleanProperty.create("z");

    public GirderBlock(Properties properties) {
        super(properties.noOcclusion().forceSolidOn());
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(X_AXIS, false)
                .setValue(Y_AXIS, false)
                .setValue(Z_AXIS, false)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if(state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        boolean X = state.getValue(X_AXIS);
        boolean Y = state.getValue(Y_AXIS);
        boolean Z = state.getValue(Z_AXIS);

        boolean neighbourCanConnect;
        boolean oppositeNeighbourCanConnect;

        if(direction != Direction.UP && direction != Direction.DOWN) {
            neighbourCanConnect = neighborState.is(this) || neighborState.getBlock() instanceof IronBarsBlock || neighborState.getBlock() instanceof WallBlock;
            oppositeNeighbourCanConnect = level.getBlockState(pos.relative(direction.getOpposite(), 1)).is(this) || level.getBlockState(pos.relative(direction.getOpposite(), 1)).getBlock() instanceof IronBarsBlock || level.getBlockState(pos.relative(direction.getOpposite(), 1)).getBlock() instanceof WallBlock;
        } else {
            neighbourCanConnect = neighborState.is(this) ? neighborState.getValue(Y_AXIS) : false;
            oppositeNeighbourCanConnect = level.getBlockState(pos.relative(direction.getOpposite(), 1)).is(this) ? level.getBlockState(pos.relative(direction.getOpposite(), 1)).getValue(Y_AXIS) : false;
        }

        boolean isLastConnection = (state.getValue(Y_AXIS) ^ state.getValue(X_AXIS) ^ state.getValue(Z_AXIS)) && !(state.getValue(Y_AXIS) && state.getValue(X_AXIS) && state.getValue(Z_AXIS));

        boolean update = (neighbourCanConnect || oppositeNeighbourCanConnect);

        switch(direction) {
            case WEST, EAST -> X = update;
            case NORTH, SOUTH -> Z = update;
            case UP, DOWN -> Y = update;
        }

        if(!(isLastConnection && !update)) {
            level.setBlock(pos, state.setValue(Y_AXIS, Y).setValue(X_AXIS, X).setValue(Z_AXIS, Z), 3);
        }

        return state;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Block.box(0, 0, 0, 16, 16, 16);
        boolean fallback = true;

        if(state.getValue(X_AXIS)) {
            shape = SHAPE_X;
            fallback = false;
        }
        if(state.getValue(Y_AXIS)) {
            shape = fallback ? SHAPE_Y : Shapes.join(shape, SHAPE_Y, BooleanOp.OR);
            fallback = false;
        }
        if(state.getValue(Z_AXIS)) {
            shape = fallback ? SHAPE_Z : Shapes.join(shape, SHAPE_Z, BooleanOp.OR);
        }

        return shape;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        Player player = context.getPlayer();

        if(player == null) return super.getStateForPlacement(context);

        boolean hasNeighbours = false;

        boolean Y = false;
        boolean X = false;
        boolean Z = false;

        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();

        boolean above = level.getBlockState(clickedPos.above()).is(this) ? level.getBlockState(clickedPos.above()).getValue(Y_AXIS) : false;
        boolean below = level.getBlockState(clickedPos.below()).is(this) ? level.getBlockState(clickedPos.below()).getValue(Y_AXIS) : false;

        if(above || below) {
            Y = true;
            hasNeighbours = true;
        }
        if(level.getBlockState(clickedPos.south()).is(this) || level.getBlockState(clickedPos.north()).is(this)) {
            Z = true;
            hasNeighbours = true;
        }
        if(level.getBlockState(clickedPos.east()).is(this) || level.getBlockState(clickedPos.west()).is(this)) {
            X = true;
            hasNeighbours = true;
        }

        if(player.isShiftKeyDown()) {
            if(hasNeighbours) {
                Direction direction = player.getDirection();

                switch(direction) {
                    case SOUTH, NORTH -> X = true;
                    case EAST, WEST -> Z = true;
                }
            } else {
                Y = true;
            }
        } else {
            if(!hasNeighbours) {
                Direction direction = player.getDirection();

                switch(direction) {
                    case SOUTH, NORTH -> X = true;
                    case EAST, WEST -> Z = true;
                }
            }
        }

        return Objects.requireNonNull(super.getStateForPlacement(context)).setValue(Y_AXIS, Y).setValue(X_AXIS, X).setValue(Z_AXIS, Z).setValue(WATERLOGGED, level.getFluidState(clickedPos).is(Fluids.WATER));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(X_AXIS, Y_AXIS, Z_AXIS, WATERLOGGED);
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public boolean onWrench(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific, Player player) {

        // shoddy behaviour

        if(player.isShiftKeyDown()) {
            if(!state.getValue(Z_AXIS) && !state.getValue(X_AXIS)) return false;
            level.setBlock(pos, state.cycle(Y_AXIS), 3);
        } else {
            if(state.getValue(Z_AXIS) && state.getValue(X_AXIS)) return false;
            level.setBlock(pos, state.cycle(Z_AXIS).cycle(X_AXIS), 3);
        }
        return true;
    }
}