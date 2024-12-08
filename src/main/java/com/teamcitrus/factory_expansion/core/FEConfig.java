package com.teamcitrus.factory_expansion.core;

import net.neoforged.neoforge.common.ModConfigSpec;

public class FEConfig {

    public static ModConfigSpec CLIENT_CONFIG;

    public static ModConfigSpec.BooleanValue DISPLAY_FLICKERING;
    public static ModConfigSpec.BooleanValue DISPLAY_COLOUR_VARIATION;
    public static ModConfigSpec.BooleanValue DISPLAY_CHROMATIC_ABERRATION;
    public static ModConfigSpec.BooleanValue DISPLAY_INFINITE_RENDER;

    public static ModConfigSpec.BooleanValue BLOCK_PLACEMENT_PREVIEW;
    public static ModConfigSpec.BooleanValue BLOCK_WRENCH_PARTICLES;
    public static ModConfigSpec.BooleanValue BLOCK_DYE_PARTICLES;

    static {
        ModConfigSpec.Builder CLIENT = new ModConfigSpec.Builder();

        CLIENT.push("displays");

        DISPLAY_FLICKERING = CLIENT.comment("if displays should flicker").define("displayFlickering", true);
        DISPLAY_COLOUR_VARIATION = CLIENT.comment("if display colours should vary").define("displayColourVariation", true);
        DISPLAY_CHROMATIC_ABERRATION = CLIENT.comment("if displays should apply chromatic aberration").define("displayChromaticAberration", true);
        DISPLAY_INFINITE_RENDER = CLIENT.comment("if displays can render from any distance").define("displayInfiniteRender", true);

        CLIENT.pop();

        CLIENT.push("block utils");

        BLOCK_PLACEMENT_PREVIEW = CLIENT.comment("if blocks can have a placement preview").define("placementPreview", true);
        BLOCK_WRENCH_PARTICLES = CLIENT.comment("if rotating blocks with a wrench should spawn particles").define("wrenchParticles", true);
        BLOCK_DYE_PARTICLES = CLIENT.comment("if applying dye to a block should spawn particles").define("dyeParticles", true);

        CLIENT.pop();

        CLIENT_CONFIG = CLIENT.build();
    }

    public static ModConfigSpec COMMON_CONFIG;

    public static ModConfigSpec.BooleanValue BLOCK_CONSUME_DYE;
    public static ModConfigSpec.BooleanValue BLOCK_APPLY_DYE;

    static {
        ModConfigSpec.Builder COMMON = new ModConfigSpec.Builder();

        COMMON.push("block utils");

        BLOCK_CONSUME_DYE = COMMON.comment("if dyes should be consumed when applied to a block").define("consumeDye", true);
        BLOCK_APPLY_DYE = COMMON.comment("if dyes can be applied to blocks at all").define("enableDye", true);

        COMMON.pop();

        COMMON_CONFIG = COMMON.build();
    }
}
