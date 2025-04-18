package com.elysiasilly.fne.core.registry;

import com.elysiasilly.fne.common.canister.BlazeCanister;
import com.elysiasilly.fne.common.canister.Canister;
import com.elysiasilly.fne.common.canister.NitroCanister;
import com.elysiasilly.fne.common.canister.SoulCanister;
import com.elysiasilly.fne.core.FERegistries;
import com.elysiasilly.fne.FactoExpa;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FECanisterTypes {

    public static final DeferredRegister<Canister> CANISTER = DeferredRegister.create(FERegistries.CANISTER_TYPE, FactoExpa.MODID);

    public static final DeferredHolder<Canister, BlazeCanister> BLAZE =
            CANISTER.register("blaze", BlazeCanister::new);

    public static final DeferredHolder<Canister, SoulCanister> SOUL =
            CANISTER.register("soul", SoulCanister::new);

    public static final DeferredHolder<Canister, NitroCanister> NITRO =
            CANISTER.register("nitro", NitroCanister::new);

}
