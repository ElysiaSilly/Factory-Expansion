package com.teamcitrus.factory_expansion.common.item.CycleBlockItem;

public enum CycleMode {

    RANDOM_ONLY(true),
    CYCLE_ONLY(false),
    RANDOM_AND_CYCLE(true);

    public final boolean random;

    CycleMode(boolean random) {
        this.random = random;
    }
}