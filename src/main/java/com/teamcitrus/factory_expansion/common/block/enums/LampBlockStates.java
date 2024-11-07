package com.teamcitrus.factory_expansion.common.block.enums;

import net.minecraft.util.StringRepresentable;

public enum LampBlockStates implements StringRepresentable {
    NORMAL  ("normal"),
    INVERTED("inverted"),
    BLINKING("blinking");

    private final String name;

    LampBlockStates(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
