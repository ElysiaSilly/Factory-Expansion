package com.teamcitrus.factory_expansion.common.block.interfaces;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public interface IWrenchableBlock {

    // called when a wrench is used on the block, boolean it returns is for whether the interaction is successful or not
    boolean onWrench(Level level, BlockPos pos, BlockState state, Direction direction, Vec3 posSpecific);
}
