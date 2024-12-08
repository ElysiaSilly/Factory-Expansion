package com.teamcitrus.factory_expansion.core.keys;

import com.teamcitrus.factory_expansion.common.data.dyeing.DyeData;
import com.teamcitrus.factory_expansion.common.data.dyeing.DyeingData;
import com.teamcitrus.factory_expansion.common.data.flamethrower.CanisterData;
import com.teamcitrus.factory_expansion.common.canister.CanisterType;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

public class FEResourceKeys {

    public static class registries{

        public static final ResourceKey<Registry<DyeingData>> DYEING
                = ResourceKey.createRegistryKey(FactoExpa.location("dyeing"));

        public static final ResourceKey<Registry<DyeData>> DYE
                = ResourceKey.createRegistryKey(FactoExpa.location("dye"));

        public static final ResourceKey<Registry<CanisterData>> CANISTER
                = ResourceKey.createRegistryKey(FactoExpa.location("canister"));

        public static final ResourceKey<Registry<CanisterType>> CANISTER_TYPE
                = ResourceKey.createRegistryKey(FactoExpa.location("canister_type"));

    }
}
