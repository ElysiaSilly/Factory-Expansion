package com.teamcitrus.factory_expansion.core;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    public static ModConfigSpec CLIENT_CONFIG;

    public static ModConfigSpec.BooleanValue DISPLAY_FLICKERING;
    public static ModConfigSpec.BooleanValue DISPLAY_COLOUR_VARIATION;

    static {
        ModConfigSpec.Builder CLIENT = new ModConfigSpec.Builder();

        CLIENT.push("visual");

        DISPLAY_FLICKERING = CLIENT.comment("if displays should flicker").define("displayFlickering", true);
        DISPLAY_COLOUR_VARIATION = CLIENT.comment("if display colours should vary").define("displayColourVariation", true);

        CLIENT.pop();

        CLIENT_CONFIG = CLIENT.build();
    }

    public static ModConfigSpec COMMON_CONFIG;

    static {
        ModConfigSpec.Builder COMMON = new ModConfigSpec.Builder();

        //COMMON.pop();

        COMMON_CONFIG = COMMON.build();
    }
}
