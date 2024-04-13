package dev.teamcitrusmods.factory_expansion.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import org.jetbrains.annotations.Nullable;

public class ColumnBlock extends RotatedPillarBlock
{
    /* case x
     *   head=east
     *   tail=west
     * case y
     *   head=up
     *   tail=down
     * case z
     *   head=south
     *   tail=north
     */

    public static final BooleanProperty HEAD = BooleanProperty.create("head");
    public static final BooleanProperty TAIL = BooleanProperty.create("tail");

    public ColumnBlock(Properties pProperties)
    {
        super(pProperties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(HEAD, false)
                .setValue(TAIL, false));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext pContext)
    {
        // CALLED BY THE AIR BLOCK YOU'RE GONNA PLACE THIS NEW BLOCK IN
        // CHECK CLICKED FACE TO GET NEIGHBOR??
        
        // useful variables
        var currentPos = pContext.getClickedPos();
        var clickedFace = pContext.getClickedFace();

        boolean currentHead = false;
        boolean currentTail = false;

        BlockPos neighborPos = null;

        switch (clickedFace) {
            case EAST:
                neighborPos = currentPos.west();
                break;
            case WEST:
                neighborPos = currentPos.east();
                break;
            case UP:
                neighborPos = currentPos.below();
                break;
            case DOWN:
                neighborPos = currentPos.above();
                break;
            case SOUTH:
                neighborPos = currentPos.north();
                break;
            case NORTH:
                neighborPos = currentPos.south();
                break;
        }

        var level = pContext.getLevel();
        BlockState neighborState = level.getBlockState(neighborPos);

        var currentBlock = this.asBlock();
        var currentAxis = clickedFace.getAxis();

        if(neighborState.getBlock() == currentBlock)
        {
            // booleans that describe neighbor block values
            var neighborAxis = neighborState.getValue(AXIS);

            if(currentAxis == neighborAxis) {
                switch (neighborAxis) {
                    case X: // east to west
                        // true: current is east of neighbor
                        if (currentPos.getX() > neighborPos.getX()) {
                            currentTail = true;
                        } else {
                            currentHead = true;
                        }
                        break;
                    case Y: // up to down
                        // true: current is over neighbor
                        if (currentPos.getY() > neighborPos.getY()) {
                            currentTail = true;
                        } else {
                            currentHead = true;
                        }
                        break;
                    case Z: // south to north
                        // true: current is south of neighbor
                        if (currentPos.getZ() > neighborPos.getZ()) {
                            currentTail = true;
                        } else {
                            currentHead = true;
                        }
                        break;
                }
            }
        }
        else // if it's not a column (so another block)
        {
            // intentionally left blank
        }

        // update this blockstate
        return super.getStateForPlacement(pContext)
                .setValue(HEAD, currentHead)
                .setValue(TAIL, currentTail);
    }

    @Override
    public BlockState updateShape(BlockState currentState, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos currentPos, BlockPos neighborPos)
    {
        // NOT CALLED BY PLACED BLOCK, BUT BY NEIGHBOR BLOCKS OF THIS CLASS
        // CURRENT: A BLOCK WHO DETECTED THE NEW PLACED ONE
        // NEIGHBOR: THE NEWLY PLACED BLOCK

        // booleans that describe current block values
        boolean currentHead = currentState.getValue(HEAD);
        boolean currentTail = currentState.getValue(TAIL);

        // if new detected block is column (same column type)
        if(neighborState.getBlock() == currentState.getBlock())
        {

            // booleans that describe neighbor block values
            var neighborAxis = neighborState.getValue(AXIS);

            if(currentState.getValue(AXIS) == neighborAxis)
            {
                switch(neighborAxis)
                {
                    case X: // east to west
                        // true: current is east of neighbor
                        if(currentPos.getX() > neighborPos.getX())
                        {
                            currentTail = true;
                        }
                        else
                        {
                            currentHead = true;
                        }
                        break;
                    case Y: // up to down
                        // true: current is over neighbor
                        if(currentPos.getY() > neighborPos.getY())
                        {
                            currentTail = true;
                        }
                        else
                        {
                            currentHead = true;
                        }
                        break;
                    case Z: // south to north
                        // true: current is south of neighbor
                        if(currentPos.getZ() > neighborPos.getZ())
                        {
                            currentTail = true;
                        }
                        else
                        {
                            currentHead = true;
                        }
                        break;
                }
            }
        }
        else // if it's not a column (so another block)
        {
            switch(currentState.getValue(AXIS))
            {
                case X: // east to west
                    // true: current is east of neighbor
                    if(currentPos.getX() > neighborPos.getX())
                    {
                        currentTail = false;
                    }
                    else
                    {
                        currentHead = false;
                    }
                    break;
                case Y: // up to down
                    // true: current is over neighbor
                    if(currentPos.getY() > neighborPos.getY())
                    {
                        currentTail = false;
                    }
                    else
                    {
                        currentHead = false;
                    }
                    break;
                case Z: // south to north
                    // true: current is south of neighbor
                    if(currentPos.getZ() > neighborPos.getZ())
                    {
                        currentTail = false;
                    }
                    else
                    {
                        currentHead = false;
                    }
                    break;
            }
        }

        // update this blockstate
        return currentState
                .setValue(HEAD, currentHead)
                .setValue(TAIL, currentTail);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder)
    {
        super.createBlockStateDefinition(pBuilder);
        pBuilder.add(HEAD, TAIL);
    }
}
