package dev.teamcitrusmods.factory_expansion.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class FluxLampBlock extends DirectionalBlock {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    private static final VoxelShape[] SHAPE = {
            Block.box(4,4,6,12,12,16), // NORTH
            Block.box(0,4,4,10,12,12), // EAST
            Block.box(4,4,0,12,12,10), // SOUTH
            Block.box(6,4,4,16,12,12), // WEST
            Block.box(4,0,4,12,10,12), // UP
            Block.box(4,6,4,12,16,12), // DOWN

    };

    public FluxLampBlock(Properties pProperties) {
        super(pProperties);
        this.registerDefaultState(this.defaultBlockState()
                .setValue(LIT, false)
                .setValue(FACING, Direction.UP));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return switch (pState.getValue(FACING)) {
            case NORTH -> SHAPE[0];
            case EAST -> SHAPE[1];
            case SOUTH -> SHAPE[2];
            case WEST -> SHAPE[3];
            case UP -> SHAPE[4];
            default -> SHAPE[5]; // DOWN
        };
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext pContext) {
        return this.defaultBlockState()
                .setValue(LIT, pContext.getLevel().hasNeighborSignal(pContext.getClickedPos()))
                .setValue(FACING, pContext.getClickedFace());
    }

    @Override
    public BlockState updateShape(BlockState pState, Direction pDirection, BlockState pNeighborState, LevelAccessor pLevel, BlockPos pCurrentPos, BlockPos pNeighborPos) {
        return super.updateShape(pState, pDirection, pNeighborState, pLevel, pCurrentPos, pNeighborPos);
    }

    public void neighborChanged(BlockState pState, Level pLevel, BlockPos pPos, Block pBlock, BlockPos pFromPos, boolean pIsMoving) {
        if (!pLevel.isClientSide) {
            boolean flag = pState.getValue(LIT);
            if (flag != pLevel.hasNeighborSignal(pPos)) {
                if (flag) {
                    pLevel.scheduleTick(pPos, this, 0);
                } else {
                    pLevel.setBlock(pPos, pState.cycle(LIT), 2);
                }
            }

        }
    }

    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        if (pState.getValue(LIT) && !pLevel.hasNeighborSignal(pPos)) {
            pLevel.setBlock(pPos, pState.cycle(LIT), 2);
        }

    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(LIT, FACING);
    }
}
