package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.canister.BlazeCanisterType;
import com.teamcitrus.factory_expansion.common.canister.NitroCanisterType;
import com.teamcitrus.factory_expansion.common.canister.SoulCanisterType;
import com.teamcitrus.factory_expansion.common.canister.CanisterType;
import com.teamcitrus.factory_expansion.core.FERegistries;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FECanisterTypes {

    public static final DeferredRegister<CanisterType> CANISTER = DeferredRegister.create(FERegistries.CANISTER_TYPE, FactoExpa.MODID);

    public static final DeferredHolder<CanisterType, BlazeCanisterType> BLAZE =
            CANISTER.register("blaze", BlazeCanisterType::new);

    public static final DeferredHolder<CanisterType, SoulCanisterType> SOUL =
            CANISTER.register("soul", SoulCanisterType::new);

    public static final DeferredHolder<CanisterType, NitroCanisterType> NITRO =
            CANISTER.register("nitro", NitroCanisterType::new);

    // TODO : demeter compat, canister that changes animal gender
}
