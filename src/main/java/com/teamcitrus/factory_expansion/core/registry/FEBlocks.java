package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.block.GirderBlock;
import com.teamcitrus.factory_expansion.common.block.LampBlock;
import com.teamcitrus.factory_expansion.common.block.VariantBlock;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Supplier;

public class FEBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, FactoExpa.MODID);
    public static final DeferredRegister.Items BLOCKITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    public static final Supplier<? extends Block> BRASS_ALLOY_BLOCK
            = registerBlockItem("brass_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> ARID_ALLOY_BLOCK
            = registerBlockItem("arid_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> PALE_ALLOY_BLOCK
            = registerBlockItem("pale_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> BLACK_ALLOY_BLOCK
            = registerBlockItem("black_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));


    public static final Supplier<? extends Block> FLUX_BULB
            = registerBlockItem("flux_bulb", () -> new LampBlock(getProperties(Blocks.NETHERITE_BLOCK), false));

    public static final Supplier<? extends Block> UV_FLUX_BULB
            = registerBlockItem("uv_flux_bulb", () -> new LampBlock(getProperties(Blocks.NETHERITE_BLOCK), true));

    public static final Dictionary<DyeColor, Supplier<? extends Block>> FLUX_BULBS
            = registerColouredBlockItems("flux_bulb", () -> new LampBlock(getProperties(Blocks.NETHERITE_BLOCK), false));


    public static final Supplier<? extends Block> BRASS_GIRDER
            = registerBlockItem("brass_girder", () -> new GirderBlock(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> ARID_GIRDER
            = registerBlockItem("arid_girder", () -> new GirderBlock(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> PALE_GIRDER
            = registerBlockItem("pale_girder", () -> new GirderBlock(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> BLACK_GIRDER
            = registerBlockItem("black_girder", () -> new GirderBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    public static final Supplier<?> TRANS_BLOCK
            = registerBlockItem("trans_block", () -> new VariantBlock(getProperties(Blocks.WHITE_WOOL), 3));

    // HELPERS

    // register block and item
    private static Supplier<? extends Block> registerBlockItem(String id, Supplier<? extends Block> blockType) {
        var tempBlock = BLOCKS.register(id, blockType);
        BLOCKITEMS.registerSimpleBlockItem(tempBlock);
        return tempBlock;
    }

    // HELP!!
    // register colour set of block and item
    private static Dictionary<DyeColor, Supplier<? extends Block>> registerColouredBlockItems(String id, Supplier<? extends Block> blockType) {

        Dictionary<DyeColor, Supplier<? extends Block>> blocks = new Hashtable<>();

        for(DyeColor colour : DyeColor.values()) {
            id = String.format("%s_%s", colour.getName(), id);
            Supplier<? extends Block> block = registerBlockItem(id, blockType);
            blocks.put(colour, block);
        }
        return blocks;
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
