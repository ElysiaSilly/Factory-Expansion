package com.teamcitrus.factory_expansion.common.block.properties;

import net.minecraft.util.StringRepresentable;

public enum FluxBulbProperties implements StringRepresentable {
    NORMAL  ("normal"),
    BLINKING("blinking");

    private final String name;

    FluxBulbProperties(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
