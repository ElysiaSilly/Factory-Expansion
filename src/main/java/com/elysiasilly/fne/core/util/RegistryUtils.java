package com.elysiasilly.fne.core.util;

import com.elysiasilly.fne.common.canister.Canister;
import com.elysiasilly.fne.common.data.dyeing.DyeData;
import com.elysiasilly.fne.common.data.dyeing.DyeingData;
import com.elysiasilly.fne.core.keys.FEResourceKeys;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.atomic.AtomicReference;

public class RegistryUtils {

    public static Holder<Enchantment> getEnchantment(Level level, ResourceKey<Enchantment> resourceKey) {
        return level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(resourceKey);
    }

    public static Holder<Canister> getCanisterType(Level level, ResourceKey<Canister> resourceKey) {
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
}
