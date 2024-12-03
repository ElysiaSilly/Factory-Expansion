package com.teamcitrus.factory_expansion.core;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {

    public static ModConfigSpec CLIENT_CONFIG;

    public static ModConfigSpec.BooleanValue DISPLAY_FLICKERING;
    public static ModConfigSpec.BooleanValue DISPLAY_COLOUR_VARIATION;
    public static ModConfigSpec.BooleanValue DISPLAY_CHROMATIC_ABERRATION;

    public static ModConfigSpec.BooleanValue BLOCK_PLACEMENT_PREVIEW;

    static {
        ModConfigSpec.Builder CLIENT = new ModConfigSpec.Builder();

        CLIENT.push("displays");

        DISPLAY_FLICKERING = CLIENT.comment("if displays should flicker").define("displayFlickering", true);
        DISPLAY_COLOUR_VARIATION = CLIENT.comment("if display colours should vary").define("displayColourVariation", true);
        DISPLAY_CHROMATIC_ABERRATION = CLIENT.comment("if display should apply chromatic aberration").define("displayChromaticAberration", true);

        CLIENT.push("block placement");

        BLOCK_PLACEMENT_PREVIEW = CLIENT.comment("if blocks can have a placement preview").define("placementPreview", true);

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
