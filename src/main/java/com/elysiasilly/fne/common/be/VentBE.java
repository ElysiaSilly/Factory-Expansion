package com.elysiasilly.fne.common.be;

import com.elysiasilly.fne.core.registry.FEBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class VentBE extends BlockEntity {

    public VentBE(BlockPos pos, BlockState blockState) {
        super(FEBlockEntities.VENT_BE.get(), pos, blockState);
    }

}
