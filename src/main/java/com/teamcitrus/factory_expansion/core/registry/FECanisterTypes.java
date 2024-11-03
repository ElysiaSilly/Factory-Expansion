package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.flamethrower.BlazeCanisterType;
import com.teamcitrus.factory_expansion.common.flamethrower.NitroCanisterType;
import com.teamcitrus.factory_expansion.common.flamethrower.SoulCanisterType;
import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterType;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.FactoExpaRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FECanisterTypes {

    public static final DeferredRegister<CanisterType> CANISTER = DeferredRegister.create(FactoExpaRegistries.CANISTER_TYPE, FactoExpa.MODID);

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

}
