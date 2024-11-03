package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FEBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, FactoExpa.MODID);
    public static final DeferredRegister.Items BLOCKITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    public static final Supplier<? extends Block> BRASS_ALLOY_BLOCK
            = registerBlockItem("brass_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> DESERT_ALLOY_BLOCK
            = registerBlockItem("desert_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> PALE_ALLOY_BLOCK
            = registerBlockItem("pale_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> VANTA_ALLOY_BLOCK
            = registerBlockItem("vanta_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));

    // HELPERS

    // register block and item
    private static Supplier<? extends Block> registerBlockItem(String resourceLocation, Supplier<? extends Block> blockType) {
        var tempBlock = BLOCKS.register(resourceLocation, blockType);
        BLOCKITEMS.registerSimpleBlockItem(tempBlock);
        return tempBlock;
    }

    @SuppressWarnings("unchecked")
    // register block
    private static Supplier<Block> registerBlockItem(String resourceLocation) {
        return (Supplier<Block>) registerBlockItem(resourceLocation, () -> new Block(BlockBehaviour.Properties.of()));
    }

    // returns block properties
    private static BlockBehaviour.Properties getProperties(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block);
    }
}
