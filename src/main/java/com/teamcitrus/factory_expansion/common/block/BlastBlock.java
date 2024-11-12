package com.teamcitrus.factory_expansion.common.block;

import com.teamcitrus.factory_expansion.common.item.CycleBlockItem.CycleBlockItem;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.function.Supplier;

public class BlastBlock extends Block {

    private final Supplier<BlockItem> explodeInto;

    public BlastBlock(Properties properties, Supplier<BlockItem> explodeInto) {
        super(properties);
        this.explodeInto = explodeInto;
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        /// obtaining new block to place
        Block newBlock;
        if(explodeInto.get() instanceof CycleBlockItem cycleableInto)
            newBlock = cycleableInto.getRandomBlock(level);
        else
            newBlock = explodeInto.get().getBlock();

        /// obtaining new blockstate
        var newState = newBlock.defaultBlockState();

        level.setBlockAndUpdate(pos, newState);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newState));

        super.onBlockExploded(state, level, pos, explosion);
    }
}
