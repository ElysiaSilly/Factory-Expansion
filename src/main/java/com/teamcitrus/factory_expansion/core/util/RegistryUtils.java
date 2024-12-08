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

    public static Holder<Enchantment> getEnchantment(Level level, ResourceKey<Enchantment> resourceKey) {
        return level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(resourceKey);
    }

    public static Holder<CanisterType> getCanisterType(Level level, ResourceKey<CanisterType> resourceKey) {
        return level.registryAccess().lookupOrThrow(FEResourceKeys.registries.CANISTER_TYPE).getOrThrow(resourceKey);
    }

    // todo : im not sure exactly how performance friendly constantly doing these lookups are

    public static DyeingData getDyeingData(Level level, Block block) {
        AtomicReference<DyeingData> output = new AtomicReference<>();

        level.registryAccess().registry(FEResourceKeys.registries.DYEING).get().stream().forEach(mapping -> {
            if(mapping.isPresent(block)) {
                output.set(mapping);
            }
        });

        return output.get();
    }

    public static DyeData getDyeData(Level level, Item item) {
        AtomicReference<DyeData> output = new AtomicReference<>();

        level.registryAccess().registry(FEResourceKeys.registries.DYE).get().stream().forEach(mapping -> {
            if(mapping.checkIngredient(item)) {
                output.set(mapping);
            }
        });

        return output.get();
    }

    public static CanisterData getCanisterData(Level level, Item item) {
        AtomicReference<CanisterData> output = new AtomicReference<>();

        level.registryAccess().registry(FEResourceKeys.registries.CANISTER).get().stream().forEach(mapping -> {
            if(mapping.getItem().equals(item)) {
                output.set(mapping);
            }
        });

        return output.get();
    }
}
