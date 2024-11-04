package com.teamcitrus.factory_expansion.common.block.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public interface ITransformOnBurnBlock {

    // called when the block is burnt
    void onBurn(Level level, BlockPos pos, Direction face, BlockState state);

    // whether to spawn the block's destroy particles when burnt
    default boolean spawnDestroyParticles() {
        return true;
    }
}
