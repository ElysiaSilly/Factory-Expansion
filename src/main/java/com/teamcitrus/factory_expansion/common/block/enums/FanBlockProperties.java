package com.teamcitrus.factory_expansion.common.block.enums;

import net.minecraft.util.StringRepresentable;
import org.joml.Vector2i;

public enum FanBlockProperties implements StringRepresentable {
    TOP_LEFT    ("1", new Vector2i(-1, 1)),
    TOP         ("2", new Vector2i(0,  1)),
    TOP_RIGHT   ("3", new Vector2i(1,  1)),
    LEFT        ("4", new Vector2i(-1, 0)),
    MIDDLE      ("5", new Vector2i(0,  0)),
    RIGHT       ("6", new Vector2i(1,  0)),
    BOTTOM_LEFT ("7", new Vector2i(-1, -1)),
    BOTTOM      ("8", new Vector2i(0,  -1)),
    BOTTOM_RIGHT("9", new Vector2i(1,  -1));

    private final String name;
    private final Vector2i relativePos;

    FanBlockProperties(String name, Vector2i relativePos) {
        this.name = name;
        this.relativePos = relativePos;
    }


    @Override
    public String getSerializedName() {
        return this.name;
    }

    public Vector2i getRelativePos() {
        return this.relativePos;
    }
}
