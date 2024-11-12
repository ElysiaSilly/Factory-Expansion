package com.teamcitrus.factory_expansion.core.properties.properties;

import net.minecraft.util.StringRepresentable;

public enum WarningLightState implements StringRepresentable {
    OFF   ("off",    0),
    RED   ("red",    5),
    YELLOW("yellow", 9),
    GREEN ("green",  12);

    private final String name;
    private final int lightLevel;

    WarningLightState(String name, int lightLevel) {
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
