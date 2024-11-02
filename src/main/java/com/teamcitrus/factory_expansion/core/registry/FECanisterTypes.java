package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.flamethrower.BlazeCanister;
import com.teamcitrus.factory_expansion.common.flamethrower.Canister;
import com.teamcitrus.factory_expansion.common.event.FactoExpaRegistries;
import com.teamcitrus.factory_expansion.common.flamethrower.SoulCanister;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FECanisterTypes {

    public static final DeferredRegister<Canister> CANISTER = DeferredRegister.create(FactoExpaRegistries.CANISTER_TYPE, FactoExpa.MODID);

    public static final DeferredHolder<Canister, BlazeCanister> BLAZE_CANISTER = CANISTER.register("blaze_canister", BlazeCanister::new);
    public static final DeferredHolder<Canister, SoulCanister> SOUL_CANISTER = CANISTER.register("soul_canister", SoulCanister::new);

}
