package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.block.VariantBlock;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FEBlocks {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FactoExpa.MODID);

    public static final DeferredBlock<?> TRANS_BLOCK = BLOCKS.register("trans_block", () -> new VariantBlock(Blocks.WHITE_WOOL.properties(), 3));
}
