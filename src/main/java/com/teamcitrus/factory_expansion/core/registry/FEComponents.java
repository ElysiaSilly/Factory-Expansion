package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterComponent;
import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.FlamethrowerComponent;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static net.neoforged.neoforge.registries.DeferredRegister.createDataComponents;

public class FEComponents {

    public static final DeferredRegister.DataComponents COMPONENTS = DeferredRegister.createDataComponents(FactoExpa.MODID);

    public static final Supplier<DataComponentType<CanisterComponent>> CANISTER = COMPONENTS.registerComponentType("canister",
            builder -> builder.persistent(CanisterComponent.MAP_CODEC.codec()).networkSynchronized(CanisterComponent.STREAM_CODEC));

    public static final Supplier<DataComponentType<FlamethrowerComponent>> FLAMETHROWER = COMPONENTS.registerComponentType("flamethrower",
            builder -> builder.persistent(FlamethrowerComponent.CODEC));
}


