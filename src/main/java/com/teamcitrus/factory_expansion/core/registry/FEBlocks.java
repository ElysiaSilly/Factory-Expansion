package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.block.*;
import com.teamcitrus.factory_expansion.common.block.fauxmultiblock.FauxMultiBlock;
import com.teamcitrus.factory_expansion.common.block.fauxmultiblock.TestMulti;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.function.Supplier;

public class FEBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(BuiltInRegistries.BLOCK, FactoExpa.MODID);
    public static final DeferredRegister.Items BLOCKITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    // blocks

    public static final Supplier<? extends Block> BRASS_ALLOY_BLOCK
            = registerBlockItem("brass_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> BRASS_GRATE
            = registerBlockItem("brass_grate", () -> new WaterloggedTransparentBlock(getProperties(Blocks.COPPER_GRATE)));
    public static final Supplier<? extends Block> CUT_BRASS_ALLOY
            = registerBlockItem("cut_brass_alloy", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> BRASS_GIRDER
            = registerBlockItem("brass_girder", () -> new GirderBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    public static final Supplier<? extends Block> ARID_ALLOY_BLOCK
            = registerBlockItem("arid_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> ARID_GRATE
            = registerBlockItem("arid_grate", () -> new WaterloggedTransparentBlock(getProperties(Blocks.COPPER_GRATE)));
    public static final Supplier<? extends Block> CUT_ARID_ALLOY
            = registerBlockItem("cut_arid_alloy", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> ARID_GIRDER
            = registerBlockItem("arid_girder", () -> new GirderBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    public static final Supplier<? extends Block> PALE_ALLOY_BLOCK
            = registerBlockItem("pale_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> PALE_GRATE
            = registerBlockItem("pale_grate", () -> new WaterloggedTransparentBlock(getProperties(Blocks.COPPER_GRATE)));
    public static final Supplier<? extends Block> CUT_PALE_ALLOY
            = registerBlockItem("cut_pale_alloy", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> PALE_GIRDER
            = registerBlockItem("pale_girder", () -> new GirderBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    public static final Supplier<? extends Block> BLACK_ALLOY_BLOCK
            = registerBlockItem("black_alloy_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> BLACK_GRATE
            = registerBlockItem("black_grate", () -> new WaterloggedTransparentBlock(getProperties(Blocks.COPPER_GRATE)));
    public static final Supplier<? extends Block> CUT_BLACK_ALLOY
            = registerBlockItem("cut_black_alloy", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> BLACK_GIRDER
            = registerBlockItem("black_girder", () -> new GirderBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    public static final Supplier<? extends Block> SCRAP_METAL_BLOCK
            = registerBlockItem("scrap_metal_block", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> SCRAP_METAL_STAIRS
            = registerBlockItem("scrap_metal_stairs", () -> new StairBlock(SCRAP_METAL_BLOCK.get().defaultBlockState(), getProperties(SCRAP_METAL_BLOCK.get())));
    public static final Supplier<? extends Block> SCRAP_METAL_SLAB
            = registerBlockItem("scrap_metal_slab", () -> new SlabBlock(getProperties(SCRAP_METAL_BLOCK.get())));

    public static final Supplier<? extends Block> ORNATE_SCRAP_METAL
            = registerBlockItem("ornate_scrap_metal", () -> new Block(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> ORNATE_SCRAP_METAL_STAIRS
            = registerBlockItem("ornate_scrap_metal_stairs", () -> new StairBlock(ORNATE_SCRAP_METAL.get().defaultBlockState(), getProperties(ORNATE_SCRAP_METAL.get())));
    public static final Supplier<? extends Block> ORNATE_SCRAP_METAL_SLAB
            = registerBlockItem("ornate_scrap_metal_slab", () -> new SlabBlock(getProperties(ORNATE_SCRAP_METAL.get())));

    public static final Supplier<? extends Block> SHS_PIPING
            = registerBlockItem("shs_piping", () -> new RotatedPillarBlock(getProperties(Blocks.NETHERITE_BLOCK)));
    public static final Supplier<? extends Block> CHS_PIPING
            = registerBlockItem("chs_piping", () -> new RotatedPillarBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    public static final Supplier<? extends Block> DRYWALL
            = registerBlockItem("drywall", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(0.5f).destroyTime(0.5f)));
    public static final Supplier<? extends Block> DRYWALL_STAIRS
            = registerBlockItem("drywall_stairs", () -> new StairBlock(DRYWALL.get().defaultBlockState(), getProperties(DRYWALL.get())));
    public static final Supplier<? extends Block> DRYWALL_SLAB
            = registerBlockItem("drywall_slab", () -> new SlabBlock(getProperties(DRYWALL.get())));

    public static final Supplier<? extends Block> BLACK_INDUSTRY_CONCRETE
            = registerBlockItem("black_industry_concrete", () -> new Block(getProperties(Blocks.BLACK_CONCRETE)));
    public static final Supplier<? extends Block> CARVED_BLACK_INDUSTRY_CONCRETE
            = registerBlockItem("carved_black_industry_concrete", () -> new Block(getProperties(Blocks.BLACK_CONCRETE)));
    public static final Supplier<? extends Block> BLACK_INDUSTRY_CONCRETE_TILE
            = registerBlockItem("black_industry_concrete_tile", () -> new Block(getProperties(Blocks.BLACK_CONCRETE)));

    public static final Supplier<? extends Block> WARM_INDUSTRY_CONCRETE
            = registerBlockItem("warm_industry_concrete", () -> new Block(getProperties(Blocks.BLACK_CONCRETE)));

    public static final Supplier<? extends Block> COLD_INDUSTRY_CONCRETE
            = registerBlockItem("cold_industry_concrete", () -> new Block(getProperties(Blocks.BLACK_CONCRETE)));

    public static final Supplier<? extends Block> RED_CLINKER_BRICKS
            = registerBlockItem("red_clinker_bricks", () -> new Block(getProperties(Blocks.BRICKS)));

    public static final Supplier<? extends Block> BROWN_CLINKER_BRICKS
            = registerBlockItem("brown_clinker_bricks", () -> new Block(getProperties(Blocks.BRICKS)));

    public static final Supplier<? extends Block> GREEN_CLINKER_BRICKS
            = registerBlockItem("green_clinker_bricks", () -> new Block(getProperties(Blocks.BRICKS)));

    public static final Supplier<? extends Block> BLACK_CLINKER_BRICKS
            = registerBlockItem("black_clinker_bricks", () -> new Block(getProperties(Blocks.BRICKS)));
    // vent

    public static final Supplier<? extends Block> LARGE_INDUSTRIAL_VENT
            = registerBlock("large_industrial_vent", () -> new LargeVentBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    public static final Supplier<? extends Block> MEDIUM_INDUSTRIAL_VENT
            = registerBlock("medium_industrial_vent", () -> new MediumVentBlock(getProperties(Blocks.NETHERITE_BLOCK)));


    //public static final Supplier<? extends Block> TEST
    //        = registerBlockItem("test", () -> new FauxMultiBlock<>(getProperties(Blocks.NETHERITE_BLOCK), TestMulti.class));

    // lamps

    public static final Supplier<? extends Block> WARNING_LIGHT
            = registerBlockItem("warning_light", () -> new WarningLightBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    public static final Supplier<? extends Block> FLUX_BULB
            = registerBlock("flux_bulb", () -> new FluxBulbBlock(getProperties(Blocks.NETHERITE_BLOCK), 7, true));

    public static final Supplier<? extends Block> INVERTED_FLUX_BULB
            = registerBlock("inverted_flux_bulb", () -> new InvertedFluxBulbBlock(getProperties(Blocks.NETHERITE_BLOCK), 7, true));

    public static final Supplier<? extends Block> UV_FLUX_BULB
            = registerBlock("uv_flux_bulb", () -> new FluxBulbBlock(getProperties(Blocks.NETHERITE_BLOCK), 0, false));

    public static final Supplier<? extends Block> INVERTED_UV_FLUX_BULB
            = registerBlock("inverted_uv_flux_bulb", () -> new InvertedFluxBulbBlock(getProperties(Blocks.NETHERITE_BLOCK), 0, false));

    public static final Dictionary<DyeColor, Supplier<? extends Block>> FLUX_BULBS
            = registerDyeableBlocks("flux_bulb", () -> new FluxBulbBlock(getProperties(Blocks.NETHERITE_BLOCK), 12, false), false);

    public static final Dictionary<DyeColor, Supplier<? extends Block>> INVERTED_FLUX_BULBS
            = registerDyeableBlocks("inverted_flux_bulb", () -> new InvertedFluxBulbBlock(getProperties(Blocks.NETHERITE_BLOCK), 12, false), false);

    // other

    public static final Supplier<?> TRANS_BLOCK
            = registerBlockItem("trans_block", () -> new VariantBlock(getProperties(Blocks.WHITE_WOOL), 3));

    public static final Supplier<? extends Block> DISPLAY
            = registerBlockItem("display", () -> new DisplayBlock(getProperties(Blocks.NETHERITE_BLOCK)));

    /// blast

    public static final Supplier<?> EGG_BLOCK
            = registerBlockItem("egg_block", () -> new BlastBlock(getProperties(Blocks.WHITE_WOOL), () -> FEItems.TRANS_BLOCK.get()));

    public static final Supplier<?> LEGG_BLOCK
            = registerBlockItem("legg_block", () -> new BlastAxisBlock(getProperties(Blocks.WHITE_WOOL)));



    /// register block and item
    private static Supplier<? extends Block> registerBlockItem(String id, Supplier<? extends Block> blockType) {
        var tempBlock = BLOCKS.register(id, blockType);
        BLOCKITEMS.registerSimpleBlockItem(tempBlock);
        return tempBlock;
    }

    /// register colour set of block and item
    private static Dictionary<DyeColor, Supplier<? extends Block>> registerDyeableBlocks(String id, Supplier<? extends Block> blockType, boolean registerItem) {

        Dictionary<DyeColor, Supplier<? extends Block>> blocks = new Hashtable<>();

        for(DyeColor colour : DyeColor.values()) {
            String name = String.format("%s_%s", colour.getName(), id);
            Supplier<? extends Block> block = registerItem ? registerBlockItem(name, blockType) : registerBlock(name, blockType);
            blocks.put(colour, block);
        }
        return blocks;
    }

    /// register block
    private static Supplier<Block> registerBlock(String id, Supplier<? extends Block> blockType) {
        return BLOCKS.register(id, blockType);
    }

    /// returns block properties
    private static BlockBehaviour.Properties getProperties(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block);
    }
}
