package com.teamcitrus.factory_expansion.core.util;

import com.teamcitrus.factory_expansion.common.data.dyeing.DyeData;
import com.teamcitrus.factory_expansion.common.data.dyeing.DyeingData;
import com.teamcitrus.factory_expansion.common.data.flamethrower.CanisterData;
import com.teamcitrus.factory_expansion.common.canister.CanisterType;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.keys.FEResourceKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import java.util.concurrent.atomic.AtomicReference;

public class RegistryUtils {

    /// ResourceKeys

    public static ResourceKey<ConfiguredFeature<?, ?>> createFeatureKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, name));
    }

    /// TagKeys

    public static TagKey<Biome> createBiomeTag(String name) {
        return TagKey.create(Registries.BIOME, ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, name));
    }
    public static TagKey<Item> createItemTag(String name) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, name));
    }
    public static TagKey<Block> createBlockTag(String name) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, name));
    }

    /// Registries

    public static BlockSetType blockSetType(String name) {
        return BlockSetType.register(new BlockSetType(FactoExpa.prefix(name)));
    }

    public static WoodType woodType(String name, BlockSetType blockSet) {
        return WoodType.register(new WoodType(FactoExpa.prefix(name), blockSet));
    }

    /// Helpers

    public static Holder<Enchantment> getEnchantment(Level level, ResourceKey<Enchantment> resourceKey) {
        return level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(resourceKey);
    }

    public static Holder<CanisterType> getCanisterType(Level level, ResourceKey<CanisterType> resourceKey) {
        return level.registryAccess().lookupOrThrow(FEResourceKeys.CANISTER_TYPE).getOrThrow(resourceKey);
    }

    // todo : im not sure exactly how performance friendly constantly doing these lookups are

    public static DyeingData getDyeingData(Level level, Block block) {
        AtomicReference<DyeingData> output = new AtomicReference<>();

        level.registryAccess().registry(FEResourceKeys.DYEING).get().stream().forEach(mapping -> {
            if(mapping.isPresent(block)) {
                output.set(mapping);
            }
        });

        return output.get();
    }

    public static DyeData getDyeData(Level level, Item item) {
        AtomicReference<DyeData> output = new AtomicReference<>();

        level.registryAccess().registry(FEResourceKeys.DYE).get().stream().forEach(mapping -> {
            if(mapping.checkIngredient(item)) {
                output.set(mapping);
            }
        });

        return output.get();
    }

    public static CanisterData getCanisterData(Level level, Item item) {
        AtomicReference<CanisterData> output = new AtomicReference<>();

        level.registryAccess().registry(FEResourceKeys.CANISTER).get().stream().forEach(mapping -> {
            if(mapping.getItem().equals(item)) {
                output.set(mapping);
            }
        });

        return output.get();
    }
}
