package com.teamcitrus.factory_expansion.common.block;

import com.mojang.serialization.MapCodec;
import com.teamcitrus.factory_expansion.common.block.enums.LampBlockStates;
import com.teamcitrus.factory_expansion.common.block.interfaces.IWrenchableBlock;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
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

public class LampBlock extends DirectionalBlock implements SimpleWaterloggedBlock, IWrenchableBlock {

    private static final BooleanProperty LIT = BlockStateProperties.LIT;
    private static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final EnumProperty<LampBlockStates> MODE = EnumProperty.create("mode", LampBlockStates.class);

    private final boolean emitParticles;

    private static final VoxelShape[] SHAPE = {
            Block.box(4,4,5,12,12,16), // NORTH
            Block.box(0,4,4,11,12,12), // EAST
            Block.box(4,4,0,12,12,11), // SOUTH
            Block.box(5,4,4,16,12,12), // WEST
            Block.box(4,0,4,12,11,12), // UP
            Block.box(4,5,4,12,16,12), // DOWN

    };

    public LampBlock(Properties properties, int light, boolean emitParticles) {
        super(properties.noOcclusion().lightLevel((state) -> state.getValue(LIT) ?  light : 0));
        this.emitParticles = emitParticles;
        this.registerDefaultState(this.getStateDefinition().any()
                .setValue(LIT, false)
                .setValue(WATERLOGGED, false)
                .setValue(MODE, LampBlockStates.NORMAL)
                .setValue(FACING, Direction.DOWN)
        );
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {return null;}

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE[0];
            case EAST  -> SHAPE[1];
            case SOUTH -> SHAPE[2];
            case WEST  -> SHAPE[3];
            case UP    -> SHAPE[4];
            case DOWN  -> SHAPE[5];
        };
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(LIT, context.getLevel().hasNeighborSignal(context.getClickedPos()))
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
    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if(state.getValue(LIT) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(LIT), 2);
        }
    }

    @Override
    public boolean onWrenchUse(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific, Player player) {

        level.setBlock(pos, state.cycle(MODE), 3);

        return true;
    }

    @Override
    public void onWrenchHover(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific, Player player) {

        player.displayClientMessage(Component.literal("cycling through modes: " + state.getValue(MODE).getSerializedName() + " -> " + state.cycle(MODE).getValue(MODE).getSerializedName()).withStyle(ChatFormatting.GRAY), true);
    }

    @Override
    public boolean overrideDefaultWrenchBehaviour() {
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, WATERLOGGED, MODE, FACING);
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
        if(level.isClientSide) return;

        if(state.getValue(LIT) != level.hasNeighborSignal(pos)) {
            if(state.getValue(LIT)) {
                level.scheduleTick(pos, this, 2);
            } else {
                level.setBlock(pos, state.cycle(LIT), 2);
            }
        }
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        if (state.getValue(LIT) && emitParticles) {
            double d0 = (double)pos.getX() + 0.5 + (random.nextDouble() - 0.4) * 0.2;
            double d1 = (double)pos.getY() + 0.7 + (random.nextDouble() - 0.9) * 0.2;
            double d2 = (double)pos.getZ() + 0.5 + (random.nextDouble() - 0.4) * 0.2;
            level.addParticle(DustParticleOptions.REDSTONE, d0, d1, d2, 0.0, 0.0, 0.0);
        }
    }
}
