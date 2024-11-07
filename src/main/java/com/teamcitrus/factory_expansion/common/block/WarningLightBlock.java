package com.teamcitrus.factory_expansion.common.block;

import com.mojang.serialization.MapCodec;
import com.teamcitrus.factory_expansion.common.block.enums.WarningLightStates;
import com.teamcitrus.factory_expansion.common.block.interfaces.IWrenchableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class WarningLightBlock extends DirectionalBlock implements SimpleWaterloggedBlock, IWrenchableBlock {

    public static final EnumProperty<WarningLightStates> STATE = EnumProperty.create("state", WarningLightStates.class);
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final BooleanProperty POWERED = BlockStateProperties.POWERED;

    private static final VoxelShape[] SHAPE = {
            Block.box(4,4,0,12,12,16), // NORTH, SOUTH
            Block.box(0,4,4,16,12,12), // EAST, WEST
            Block.box(4,0,4,12,16,12), // UP, DOWN

    };

    public WarningLightBlock(Properties properties) {
        super(properties.noOcclusion().lightLevel((state) -> state.getValue(STATE).lightLevel()));
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(STATE, WarningLightStates.OFF)
                .setValue(WATERLOGGED, false)
                .setValue(FACING, Direction.UP)
                .setValue(POWERED, false)
        );
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH, SOUTH -> SHAPE[0];
            case EAST, WEST   -> SHAPE[1];
            case UP, DOWN     -> SHAPE[2];
        };
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getClickedFace())
                .setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).is(Fluids.WATER));
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {
        if(state.getValue(WATERLOGGED)) {
            level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return state;
    }

    @Override
    public boolean onWrench(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific, Player player) {
        return false;
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return null;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(STATE, WATERLOGGED, FACING, POWERED);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(level.isClientSide) return;

        if(state.getValue(POWERED) != level.hasNeighborSignal(pos)) {
            if(state.getValue(POWERED)) {
                level.scheduleTick(pos, this, 2);
            } else {
                level.setBlock(pos, state.cycle(POWERED).cycle(STATE), 3);
            }
        }
    }

    @Override
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(state.getValue(POWERED) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(POWERED), 2);
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return (15 / 3) * state.getValue(STATE).ordinal();
    }
}
