package com.teamcitrus.factory_expansion.core.keys;

import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;

public class FETags {

    public static class Blocks{

        public static final TagKey<Block> GIRDERS_CONNECT
                = blockTag(FactoExpa.location("girders_connect"));

        public static final TagKey<Block> WRENCH_CONFIGURE_BLACKLIST
                = blockTag(FactoExpa.location("wrench_configure_blacklist"));

        public static final TagKey<Block> WRENCH_PICKUP_WHITELIST
                = blockTag(FactoExpa.location("wrench_pickup_whitelist"));

        public static final TagKey<Block> HAS_PLACEMENT_PREVIEW
                = blockTag(FactoExpa.location("has_placement_preview"));

    }

    public static class Items{

        public static final TagKey<Item> WRENCH
                = itemTag("c", "tools/wrench");

    }

    public static TagKey<Item> itemTag(String namespace, String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(namespace, name));
    }

    public static TagKey<Item> itemTag(ResourceLocation location) {
        return TagKey.create(Registries.ITEM, location);
    }

    public static TagKey<Block> blockTag(String namespace, String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(namespace, name));
    }

    public static TagKey<Block> blockTag(ResourceLocation location) {
        return TagKey.create(Registries.BLOCK, location);
    }
}
