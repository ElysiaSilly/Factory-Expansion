package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.block.be.DisplayBE;
import com.teamcitrus.factory_expansion.common.block.be.VentBE;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FEBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FactoExpa.MODID);

    public static final Supplier<BlockEntityType<DisplayBE>> DISPLAY_BE = BLOCKENTITIES.register(
            "display_be", () -> BlockEntityType.Builder.of(DisplayBE::new, FEBlocks.DISPLAY.get()).build(null));

    public static final Supplier<BlockEntityType<VentBE>> VENT_BE = BLOCKENTITIES.register(
            "vent_be", () -> BlockEntityType.Builder.of(VentBE::new, FEBlocks.LARGE_BLACK_VENT.get()).build(null));
}
