package com.teamcitrus.factory_expansion.core.properties.properties;

import com.teamcitrus.factory_expansion.common.block.interfaces.BlockPosRepresentable;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;

public enum MediumVentBlocks implements StringRepresentable, BlockPosRepresentable {
    TOP_LEFT    ("1", new BlockPos(-1, 0, 1)),
    TOP         ("2", new BlockPos(0, 0, 1)),
    LEFT        ("3", new BlockPos(-1,0, 0)),
    MIDDLE      ("4", new BlockPos(0, 0, 0));

    private final String name;
    private final BlockPos relativePos;

    MediumVentBlocks(String name, BlockPos relativePos) {
        this.name = name;
        this.relativePos = relativePos;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    @Override
    public BlockPos getRelativePos() {
        return this.relativePos;
    }
}
