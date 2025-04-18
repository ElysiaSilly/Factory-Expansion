package com.elysiasilly.fne.core.registry;

import com.elysiasilly.fne.common.component.CanisterComponent;
import com.elysiasilly.fne.common.component.FlamethrowerComponent;
import com.elysiasilly.fne.common.component.SkinTokenComponent;
import com.elysiasilly.fne.common.data.wrench.WrenchComponent;
import com.elysiasilly.fne.FactoExpa;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FEComponents {

    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, FactoExpa.MODID);

    public static final Supplier<DataComponentType<CanisterComponent>> CANISTER = COMPONENTS.registerComponentType("canister",
            builder -> builder.persistent(CanisterComponent.CODEC).networkSynchronized(CanisterComponent.STREAM));

    public static final Supplier<DataComponentType<FlamethrowerComponent>> FLAMETHROWER = COMPONENTS.registerComponentType("flamethrower",
            builder -> builder.persistent(FlamethrowerComponent.CODEC).networkSynchronized(FlamethrowerComponent.STREAM));

    public static final Supplier<DataComponentType<SkinTokenComponent>> SKIN_TOKEN = COMPONENTS.registerComponentType("skin_token",
            builder -> builder.persistent(SkinTokenComponent.CODEC).networkSynchronized(SkinTokenComponent.STREAM));

    public static final Supplier<DataComponentType<WrenchComponent>> WRENCH = COMPONENTS.registerComponentType("wrench",
            builder -> builder.persistent(WrenchComponent.CODEC));
}


