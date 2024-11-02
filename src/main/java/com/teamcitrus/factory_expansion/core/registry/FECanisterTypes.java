package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.flamethrower.BlazeCanisterType;
import com.teamcitrus.factory_expansion.common.flamethrower.CanisterType;
import com.teamcitrus.factory_expansion.common.event.FactoExpaRegistries;
import com.teamcitrus.factory_expansion.common.flamethrower.SoulCanisterType;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FECanisterTypes {

    public static final DeferredRegister<CanisterType> CANISTER = DeferredRegister.create(FactoExpaRegistries.CANISTER_TYPE, FactoExpa.MODID);

    public static final DeferredHolder<CanisterType, BlazeCanisterType> BLAZE_CANISTER = CANISTER.register("blaze", BlazeCanisterType::new);
    public static final DeferredHolder<CanisterType, SoulCanisterType> SOUL_CANISTER = CANISTER.register("soul", SoulCanisterType::new);

}
