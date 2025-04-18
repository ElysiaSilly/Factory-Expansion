package com.elysiasilly.fne.core;

import com.elysiasilly.fne.common.canister.Canister;
import com.elysiasilly.fne.core.keys.FEResourceKeys;
import net.minecraft.core.Registry;
import net.neoforged.neoforge.registries.RegistryBuilder;

public class FERegistries {

    public static final Registry<Canister> CANISTER_TYPE = new RegistryBuilder<>(FEResourceKeys.registries.CANISTER_TYPE).sync(true).create();
}
