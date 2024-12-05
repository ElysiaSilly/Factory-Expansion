package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.block.be.DisplayBlockBE;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FEBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCKENTITIES = DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, FactoExpa.MODID);

    public static final Supplier<BlockEntityType<DisplayBlockBE>> DISPLAY_BE = BLOCKENTITIES.register(
            "display_be", () -> BlockEntityType.Builder.of(DisplayBlockBE::new, FEBlocks.DISPLAY.get()).build(null));

}
