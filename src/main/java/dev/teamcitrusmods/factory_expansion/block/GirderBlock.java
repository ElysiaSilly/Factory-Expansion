package dev.teamcitrusmods.factory_expansion.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class GirderBlock extends RotatedPillarBlock
{
    // YMODE defines the rotation of the block when it's placed on the Y axis
    // true follows Z, false follows X
    public static final BooleanProperty YMODE = BooleanProperty.create("ymode");

    // defining the shapes
    // [0]: X axis
    // [1]: Y axis, following Z
    // [1]: Y axis, following X
    // [1]: Z axis
    private static final VoxelShape[] girderShapes = {
            Shapes.or(
                    Block.box(0, 0, 4, 16, 2, 12),
                    Block.box(0, 2, 7, 16, 14, 9),
                    Block.box(0, 14, 4, 16, 16, 12)),
            Shapes.or(
                    Block.box(0, 0, 4, 2, 16, 12),
                    Block.box(2, 0, 7, 14, 16, 9),
                    Block.box(14, 0, 4, 16, 16, 12)),
            Shapes.or(
                    Block.box(4, 0, 0, 12, 16, 2),
                    Block.box(7, 0, 2, 9, 16, 14),
                    Block.box(4, 0, 14, 12, 16, 16)),
            Shapes.or(
                    Block.box(4, 0, 0, 12, 2, 16),
                    Block.box(7, 2, 0, 9, 14, 16),
                    Block.box(4, 14, 0, 12, 16, 16))
    };

    public GirderBlock(Properties pProperties)
    {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(YMODE, false));
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext)
    {
        VoxelShape shape = null;

        // shape changs based on the axis and the yMode
        switch (pState.getValue(AXIS))
        {
            case X:
                shape = girderShapes[0];
                break;
            case Y:
                if(pState.getValue(YMODE))
                    shape = girderShapes[1];
                else
                    shape = girderShapes[2];
                break;
            case Z:
                shape = girderShapes[3];
                break;
        }

        return shape;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        // get player and place block pos
        var playerPos = pContext.getPlayer().position();
        var currentPos = pContext.getClickedPos();

        // get absolute x and z distance between player and block
        var xDiff = Math.abs(playerPos.x - currentPos.getX());
        var zDiff = Math.abs(playerPos.z - currentPos.getZ());

        // yMode follows Z if the Z distance between the player-block is less than the X distance
        boolean yMode = xDiff > zDiff;

        return super.getStateForPlacement(pContext)
                .setValue(YMODE, yMode);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(YMODE);
    }
}
