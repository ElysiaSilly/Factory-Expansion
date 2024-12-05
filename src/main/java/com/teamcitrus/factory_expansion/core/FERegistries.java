package com.teamcitrus.factory_expansion.core;

import com.teamcitrus.factory_expansion.common.data.colourapplication.ColourMappingData;
import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class FERegistries {

    // the resourcekey for our custom registry (factory_expansion:canister_type)
    public static final ResourceKey<Registry<CanisterType>> CANISTER_TYPE_REGISTRY_KEY
            = ResourceKey.createRegistryKey(FactoExpa.location("canister_type"));

    public static final ResourceKey<Registry<ColourMappingData>> COLOUR_MAPPING
            = ResourceKey.createRegistryKey(FactoExpa.location("colour_mapping"));

    // our custom registry
    public static final Registry<CanisterType> CANISTER_TYPE = new RegistryBuilder<>(CANISTER_TYPE_REGISTRY_KEY)
            .defaultKey(ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, "empty")) // default value, like how items and blocks default to minecraft:air
            .create();
}
