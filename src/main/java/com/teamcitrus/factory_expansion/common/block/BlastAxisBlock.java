package com.teamcitrus.factory_expansion.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.gameevent.GameEvent;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class BlastAxisBlock<T extends Comparable<T>, V extends T> extends RotatedPillarBlock {

    private final Supplier<Block/*Item*/> explodeInto;

    /// don't like
    private BlockState newerState = null;

    public BlastAxisBlock(Properties properties) {
        super(properties);
        this.explodeInto = () -> Blocks.OAK_LOG;
    }

    @Override
    public void onBlockExploded(BlockState state, Level level, BlockPos pos, Explosion explosion) {
        if(level.isClientSide()) return;

        var properties = state.getProperties();

        var newState = explodeInto.get().defaultBlockState();

        for(var property : properties) {
            Property<T> propT = (Property<T>) property;
            V valueV = (V) state.getValue(propT);

            newState = newState.setValue(propT, valueV);
        }

        this.newerState = newState;

        super.onBlockExploded(state, level, pos, explosion);
    }

    @Override
    public void wasExploded(Level level, BlockPos pos, Explosion explosion) {

        if(newerState != null)
        {
            level.setBlockAndUpdate(pos, newerState);
            level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(newerState));

        }
        super.wasExploded(level, pos, explosion);
    }
}
