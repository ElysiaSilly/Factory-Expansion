package com.teamcitrus.factory_expansion.common.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.client.render.misc.MiscRendering;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IPreviewBlock;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IWrenchableBlock;
import com.teamcitrus.factory_expansion.core.keys.FEBlockTags;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Objects;

public class GirderBlock extends Block implements SimpleWaterloggedBlock, IWrenchableBlock, IPreviewBlock {

    private static final VoxelShape SHAPE_X = Block.box(0, 0, 4, 16, 16, 12);
    private static final VoxelShape SHAPE_Y = Block.box(4, 0, 4, 12, 16, 12);
    private static final VoxelShape SHAPE_Z = Block.box(4, 0, 0, 12, 16, 16);

    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final BooleanProperty X_AXIS = BooleanProperty.create("x");
    public static final BooleanProperty Y_AXIS = BooleanProperty.create("y");
    public static final BooleanProperty Z_AXIS = BooleanProperty.create("z");

    public GirderBlock(Properties properties) {
        super(properties.forceSolidOn());
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(X_AXIS, false)
                .setValue(Y_AXIS, false)
                .setValue(Z_AXIS, false)
                .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos) {

        if(state.getValue(WATERLOGGED)) level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));

        if(direction.getAxis() == Direction.Axis.Y && !neighborState.is(this)) return state;

        TagKey<Block> girderFriend = FEBlockTags.GIRDERS_CONNECT;

        boolean X = state.getValue(X_AXIS);
        boolean Y = state.getValue(Y_AXIS);
        boolean Z = state.getValue(Z_AXIS);

        boolean neighbourCanConnect;
        boolean oppositeNeighbourCanConnect;

        BlockPos opposite = pos.relative(direction.getOpposite());

        if(direction.getAxis() != Direction.Axis.Y) {
            neighbourCanConnect = neighborState.is(girderFriend);
            oppositeNeighbourCanConnect = level.getBlockState(opposite).is(girderFriend);
        } else {
            neighbourCanConnect = neighborState.getBlock() instanceof GirderBlock ? neighborState.getValue(Y_AXIS) : false;
            oppositeNeighbourCanConnect = level.getBlockState(opposite).getBlock() instanceof GirderBlock ? level.getBlockState(opposite).getValue(Y_AXIS) : false;
        }

        boolean isLastConnection = Y ^ X ^ Z && !(Y && X);

        boolean update = (neighbourCanConnect || oppositeNeighbourCanConnect);

        switch(direction.getAxis()) {
            case Direction.Axis.X -> X = update;
            case Direction.Axis.Z -> Z = update;
            case Direction.Axis.Y -> Y = update;
        }

        if(!(isLastConnection && !update)) level.setBlock(pos, state.setValue(Y_AXIS, Y).setValue(X_AXIS, X).setValue(Z_AXIS, Z), 3);

        return state;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {

        VoxelShape shape = SHAPE_Y;

        if(state.getValue(X_AXIS)) shape = Shapes.join(shape, SHAPE_X, BooleanOp.OR);

        if(state.getValue(Z_AXIS)) shape = Shapes.join(shape, SHAPE_Z, BooleanOp.OR);

        return shape;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {

        Player player = context.getPlayer();

        if(player == null) return super.getStateForPlacement(context);

        boolean Y = false;
        boolean X = false;
        boolean Z = false;

        BlockPos clickedPos = context.getClickedPos();
        Level level = context.getLevel();

        boolean above = level.getBlockState(clickedPos.above()).getBlock() instanceof GirderBlock ? level.getBlockState(clickedPos.above()).getValue(Y_AXIS) : false;
        boolean below = level.getBlockState(clickedPos.below()).getBlock() instanceof GirderBlock ? level.getBlockState(clickedPos.below()).getValue(Y_AXIS) : false;

        if(above || below) Y = true;

        if(level.getBlockState(clickedPos.south()).getBlock() instanceof GirderBlock || level.getBlockState(clickedPos.north()).getBlock() instanceof GirderBlock) Z = true;

        if(level.getBlockState(clickedPos.east()).getBlock() instanceof GirderBlock || level.getBlockState(clickedPos.west()).getBlock() instanceof GirderBlock) X = true;

        if(player.isShiftKeyDown()) Y = true;

        if(!(X || Y || Z)) {
            Direction direction = player.getDirection();

            switch(direction.getAxis()) {
                case Direction.Axis.X -> Z = true;
                case Direction.Axis.Z -> X = true;
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
    public boolean onWrenchUse(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific, Player player) {

        if(!player.isShiftKeyDown()) {
            if(!state.getValue(Z_AXIS) && !state.getValue(X_AXIS)) return false;
            level.setBlock(pos, state.cycle(Y_AXIS), 3);
        } else {
            boolean isLast = state.getValue(Z_AXIS) ^ state.getValue(X_AXIS) ^ state.getValue(Y_AXIS) && !(state.getValue(Y_AXIS) && state.getValue(X_AXIS));

            switch(direction.getAxis()) {
                case Z -> {
                    if(isLast && state.getValue(Z_AXIS)) return false;
                    level.setBlock(pos, state.cycle(Z_AXIS), 3);
                }
                case X -> {
                    if(isLast && state.getValue(X_AXIS)) return false;
                    level.setBlock(pos, state.cycle(X_AXIS), 3);
                }
            }
        }

        return true;
    }

    @Override
    public void onWrenchHover(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific, Player player) {

    }

    @Override
    public boolean overrideDefaultWrenchBehaviour() {
        return true;
    }

    @Override
    public void renderPreview(BlockHitResult hitResult, BlockPlaceContext context, Block block, Minecraft minecraft, PoseStack stack) {
        MiscRendering.renderGhostBlock(block, context, stack);
    }
}
