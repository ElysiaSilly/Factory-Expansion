package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.data.flamethrower.BlazeCanisterType;
import com.teamcitrus.factory_expansion.common.data.flamethrower.NitroCanisterType;
import com.teamcitrus.factory_expansion.common.data.flamethrower.SoulCanisterType;
import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterType;
import com.teamcitrus.factory_expansion.core.FERegistries;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FECanisterTypes {

    public static final DeferredRegister<CanisterType> CANISTER = DeferredRegister.create(FERegistries.CANISTER_TYPE, FactoExpa.MODID);

    public static final DeferredHolder<CanisterType, BlazeCanisterType> BLAZE =
            CANISTER.register("blaze", () -> new BlazeCanisterType(
                    Component.literal("blaze"),
                    ResourceLocation.parse("null") // eventually used for an icon in the tooltip
            ));

    public static final DeferredHolder<CanisterType, SoulCanisterType> SOUL =
            CANISTER.register("soul", () -> new SoulCanisterType(
                    Component.literal("soul"),
                    ResourceLocation.parse("null")
            ));

    public static final DeferredHolder<CanisterType, NitroCanisterType> NITRO =
            CANISTER.register("nitro", () -> new NitroCanisterType(
                    Component.literal("nitro"),
                    ResourceLocation.parse("null")
            ));

    // TODO : demeter compat, canister that changes animal gender
}
