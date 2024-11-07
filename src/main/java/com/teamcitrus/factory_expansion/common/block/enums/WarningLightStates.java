package com.teamcitrus.factory_expansion.common.block.enums;

import net.minecraft.util.StringRepresentable;

public enum WarningLightStates implements StringRepresentable {
    OFF   ("off",    0),
    RED   ("red",    5),
    YELLOW("yellow", 9),
    GREEN ("green",  12);

    private final String name;
    private final int lightLevel;

    WarningLightStates(String name, int lightLevel) {
        this.name = name;
        this.lightLevel = lightLevel;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }

    public int lightLevel() {
        return this.lightLevel;
    }
}
