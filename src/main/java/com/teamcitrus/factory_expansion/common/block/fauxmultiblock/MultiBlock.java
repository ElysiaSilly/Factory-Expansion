package com.teamcitrus.factory_expansion.common.block.fauxmultiblock;

import com.teamcitrus.factory_expansion.common.block.interfaces.BlockPosRepresentable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Objects;

public class MultiBlock<T extends Enum<T> & BlockPosRepresentable & StringRepresentable> {

    // todo : make this support directions somehow ;-;

    private final Class<T> multi;
    private final FauxMultiBlock<T> block;

    protected MultiBlock(Class<T> multi, FauxMultiBlock<T> block) {
        this.multi = multi;
        this.block = block;
    }

    public boolean isValidPlacement(Direction direction, Level level, BlockPos pos) {

        boolean flag = true;

        for(T block : multi.getEnumConstants()) {
            BlockPos blockPos = block.position();
            BlockPos middle = new BlockPos(0, 0, 0);
            if(!isMiddle(blockPos)) {

                switch(direction.getAxis()) {
                    case X -> middle = middle.offset(blockPos.getY(), blockPos.getZ(), blockPos.getX());
                    case Y -> middle = middle.offset(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    case Z -> middle = middle.offset(blockPos.getX(), blockPos.getZ(), blockPos.getY());
                }

                if(!level.getBlockState(pos.offset(middle)).canBeReplaced()) {
                    flag = false;
                    break;
                }
            }
        }

        return flag;
    }

    public void place(Direction direction, Level level, BlockPos pos) {

        for(T block : multi.getEnumConstants()) {
            BlockPos blockPos = block.position();
            BlockPos middle = new BlockPos(0, 0, 0);

            if(!isMiddle(blockPos)) {

                switch(direction.getAxis()) {
                    case X -> middle = middle.offset(blockPos.getY(), blockPos.getZ(), blockPos.getX());
                    case Y -> middle = middle.offset(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    case Z -> middle = middle.offset(blockPos.getX(), blockPos.getZ(), blockPos.getY());
                }

                level.setBlock(pos.offset(middle), this.block.defaultBlockState().setValue(this.block.getBlocks(), block).setValue(this.block.getFacing(), direction), 3);
            }
        }
    }

    public void destroy(BlockState state, Direction direction, Level level, BlockPos pos) {
        T current = (T) state.getValue(this.block.getBlocks());

        if(isMiddle(current.position())) {
            for(T block : multi.getEnumConstants()) {
                BlockPos blockPos = block.position();
                if(isMiddle(current.position())) {
                    BlockPos adjusted = new BlockPos(0, 0, 0);

                    switch(direction.getAxis()) {
                        case X -> adjusted = adjusted.offset(blockPos.getY(), blockPos.getZ(), blockPos.getX());
                        case Y -> adjusted = adjusted.offset(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                        case Z -> adjusted = adjusted.offset(blockPos.getX(), blockPos.getZ(), blockPos.getY());
                    }

                    if(level.getBlockState(pos.offset(adjusted)).is(this.block)) level.destroyBlock(pos.offset(adjusted), false);
                }
            }
        } else {
            BlockPos adjusted = new BlockPos(0, 0, 0);

            switch(direction.getAxis()) {
                case X -> adjusted = adjusted.offset(0, current.position().getZ() * -1, current.position().getX() * -1);
                case Y -> adjusted = adjusted.offset(current.position().getX() * -1, 0, current.position().getZ() * -1);
                case Z -> adjusted = adjusted.offset(current.position().getX() * -1, current.position().getZ() * -1, 0);
            }

            if(level.getBlockState(pos.offset(adjusted)).is(this.block)) level.destroyBlock(pos.offset(adjusted), false);
        }
    }

    public boolean isMiddle(BlockPos pos) {
        return Objects.equals(pos, new BlockPos(0, 0, 0));
    }
}
