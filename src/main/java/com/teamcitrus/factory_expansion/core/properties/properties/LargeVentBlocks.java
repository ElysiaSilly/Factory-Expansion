package com.teamcitrus.factory_expansion.core.properties.properties;

import com.teamcitrus.factory_expansion.common.block.interfaces.BlockPosRepresentable;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;

public enum LargeVentBlocks implements StringRepresentable, BlockPosRepresentable {
    TOP_LEFT    ("1", new BlockPos(-1, 0, 1)),
    TOP         ("2", new BlockPos(0, 0, 1)),
    TOP_RIGHT   ("3", new BlockPos(1, 0, 1)),
    LEFT        ("4", new BlockPos(-1,0, 0)),
    MIDDLE      ("5", new BlockPos(0, 0, 0)),
    RIGHT       ("6", new BlockPos(1, 0, 0)),
    BOTTOM_LEFT ("7", new BlockPos(-1,0, -1)),
    BOTTOM      ("8", new BlockPos(0, 0, -1)),
    BOTTOM_RIGHT("9", new BlockPos(1,0,  -1));

    private final String name;
    private final BlockPos relativePos;

    LargeVentBlocks(String name, BlockPos relativePos) {
        this.name = name;
        this.relativePos = relativePos;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    @Override
    public BlockPos position() {
        return this.relativePos;
    }
}
