package com.teamcitrus.factory_expansion.common.block.fauxmultiblock;

import com.teamcitrus.factory_expansion.common.block.interfaces.BlockPosRepresentable;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

public enum TestMulti implements StringRepresentable, BlockPosRepresentable {
    TOP         ("2", new BlockPos(0, 0, 1)),
    LEFT        ("4", new BlockPos(-1,0, 0)),
    MIDDLE      ("5", new BlockPos(0, 0, 0)),
    RIGHT       ("6", new BlockPos(1, 0, 0)),
    BOTTOM      ("8", new BlockPos(0, 0, -1));

    private final String name;
    private final BlockPos position;

    TestMulti(String name, BlockPos position) {
        this.name = name;
        this.position = position;
    }

    @Override
    public @NotNull String getSerializedName() {
        return this.name;
    }

    @Override
    public @NotNull BlockPos position() {
        return this.position;
    }

}
