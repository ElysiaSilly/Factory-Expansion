package com.teamcitrus.factory_expansion.core;

import com.teamcitrus.factory_expansion.common.canister.CanisterType;
import com.teamcitrus.factory_expansion.core.keys.FEResourceKeys;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class FERegistries {

    public static final Registry<CanisterType> CANISTER_TYPE = new RegistryBuilder<>(FEResourceKeys.CANISTER_TYPE)
            .defaultKey(ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, "empty")) // default value, like how items and blocks default to minecraft:air
            .create();
}
