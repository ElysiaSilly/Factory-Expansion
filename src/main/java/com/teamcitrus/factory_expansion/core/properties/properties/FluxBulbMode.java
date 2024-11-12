package com.teamcitrus.factory_expansion.core.properties.properties;

import net.minecraft.util.StringRepresentable;

public enum FluxBulbMode implements StringRepresentable {
    NORMAL  ("normal"),
    BLINKING("blinking");

    private final String name;

    FluxBulbMode(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
