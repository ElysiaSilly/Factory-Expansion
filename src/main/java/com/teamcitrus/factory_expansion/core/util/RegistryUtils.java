package com.teamcitrus.factory_expansion.core.util;

import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class RegistryUtils {

    // TODO : ResourceKeys

    public static ResourceKey<ConfiguredFeature<?, ?>> createFeatureKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, name));
    }

    // TODO : TagKeys

    public static TagKey<Biome> createBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, name));
    }
    public static TagKey<Item> createItemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, name));
    }
    public static TagKey<Block> createBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, name));
    }

    // TODO : Registries

    public static BlockSetType blockSetType(String name) {
        return BlockSetType.register(new BlockSetType(FactoExpa.prefix(name)));
    }

    public static WoodType woodType(String name, BlockSetType blockSet) {
        return WoodType.register(new WoodType(FactoExpa.prefix(name), blockSet));
    }
}
