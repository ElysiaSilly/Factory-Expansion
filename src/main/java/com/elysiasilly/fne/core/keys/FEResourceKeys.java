package com.elysiasilly.fne.core.keys;

import com.elysiasilly.fne.common.canister.Canister;
import com.elysiasilly.fne.common.data.dyeing.DyeData;
import com.elysiasilly.fne.common.data.dyeing.DyeingData;
import com.elysiasilly.fne.FactoExpa;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class FEResourceKeys {

    public static class registries{

        public static final ResourceKey<Registry<DyeingData>> DYEING
                = ResourceKey.createRegistryKey(FactoExpa.location("dyeing"));

        public static final ResourceKey<Registry<DyeData>> DYE
                = ResourceKey.createRegistryKey(FactoExpa.location("dye"));

        public static final ResourceKey<Registry<Canister>> CANISTER_TYPE
                = ResourceKey.createRegistryKey(FactoExpa.location("canister_type"));

    }
}
