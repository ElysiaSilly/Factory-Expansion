package com.elysiasilly.fne.core.registry;

import com.elysiasilly.fne.common.be.DisplayBE;
import com.elysiasilly.fne.common.be.VentBE;
import com.elysiasilly.fne.FactoExpa;
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
