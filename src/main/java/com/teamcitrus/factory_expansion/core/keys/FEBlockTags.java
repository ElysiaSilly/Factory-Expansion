package com.teamcitrus.factory_expansion.core.keys;

import com.teamcitrus.factory_expansion.core.util.RegistryUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class FEBlockTags {

    public static final TagKey<Block> GIRDERS_CONNECT
            = RegistryUtils.createBlockTag("girders_connect");


    /* todo
    public static final TagKey<Block> GIRDERS
            = RegistryUtils.createBlockTag("girders");

    public static final TagKey<Block> WRENCH_BLACKLIST
            = RegistryUtils.createBlockTag("wrench_blacklist");
     */
}
