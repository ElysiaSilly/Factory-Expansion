package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.block.*;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Supplier;

public class FEBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FactoExpa.MODID);
    public static final DeferredRegister.Items BLOCKITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    // blocks

    // arid alloy
    public static final DeferredBlock<Block> ARID_ALLOY_BLOCK
            = regWithItem("arid_alloy_block", () -> new Block(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> ARID_GRATE
            = regWithItem("arid_grate", () -> new WaterloggedTransparentBlock(getProp(Blocks.COPPER_GRATE)));
    public static final DeferredBlock<Block> CUT_ARID_ALLOY
            = regWithItem("cut_arid_alloy", () -> new Block(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> CUT_ARID_ALLOY_STAIRS
            = regWithItem("cut_arid_alloy_stairs", () -> new StairBlock(getState(CUT_ARID_ALLOY), getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> CUT_ARID_ALLOY_SLAB
            = regWithItem("cut_arid_alloy_slab", () -> new SlabBlock(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> ARID_GIRDER
            = reg("arid_girder", () -> new GirderBlock(getProp(Blocks.NETHERITE_BLOCK)));

    // pale alloy
    public static final DeferredBlock<Block> PALE_ALLOY_BLOCK
            = regWithItem("pale_alloy_block", () -> new Block(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> PALE_GRATE
            = regWithItem("pale_grate", () -> new WaterloggedTransparentBlock(getProp(Blocks.COPPER_GRATE)));
    public static final DeferredBlock<Block> CUT_PALE_ALLOY
            = regWithItem("cut_pale_alloy", () -> new Block(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> CUT_PALE_ALLOY_STAIRS
            = regWithItem("cut_pale_alloy_stairs", () -> new StairBlock(getState(CUT_PALE_ALLOY), getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> CUT_PALE_ALLOY_SLAB
            = regWithItem("cut_pale_alloy_slab", () -> new SlabBlock(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> PALE_GIRDER
            = reg("pale_girder", () -> new GirderBlock(getProp(Blocks.NETHERITE_BLOCK)));

    // black alloy
    public static final DeferredBlock<Block> BLACK_ALLOY_BLOCK
            = regWithItem("black_alloy_block", () -> new Block(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> BLACK_GRATE
            = regWithItem("black_grate", () -> new WaterloggedTransparentBlock(getProp(Blocks.COPPER_GRATE)));
    public static final DeferredBlock<Block> CUT_BLACK_ALLOY
            = regWithItem("cut_black_alloy", () -> new Block(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> CUT_BLACK_ALLOY_STAIRS
            = regWithItem("cut_black_alloy_stairs", () -> new StairBlock(getState(CUT_BLACK_ALLOY), getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> CUT_BLACK_ALLOY_SLAB
            = regWithItem("cut_black_alloy_slab", () -> new SlabBlock(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> BLACK_GIRDER
            = reg("black_girder", () -> new GirderBlock(getProp(Blocks.NETHERITE_BLOCK)));

    public static final DeferredBlock<Block> LARGE_BLACK_VENT
            = reg("large_black_vent", () -> new LargeVentBlock(getProp(BLACK_ALLOY_BLOCK.get())));
    public static final DeferredBlock<Block> MEDIUM_BLACK_VENT
            = reg("medium_black_vent", () -> new MediumVentBlock(getProp(BLACK_ALLOY_BLOCK.get())));
    public static final DeferredBlock<Block> SMALL_BLACK_VENT
            = reg("small_black_vent", () -> new SmallVentBlock(getProp(BLACK_ALLOY_BLOCK.get())));

    public static final DeferredBlock<Block> BLACK_CATWALK
            = reg("black_catwalk", () -> new CatwalkBlock(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> BLACK_CATWALK_STAIRS
            = reg("black_catwalk_stairs", () -> new CatwalkStairBlock(getProp(Blocks.NETHERITE_BLOCK)));

    // scrap metal
    public static final DeferredBlock<Block> SCRAP_METAL_BLOCK
            = regWithItem("scrap_metal_block", () -> new Block(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> SCRAP_METAL_STAIRS
            = regWithItem("scrap_metal_stairs", () -> new StairBlock(SCRAP_METAL_BLOCK.get().defaultBlockState(), getProp(SCRAP_METAL_BLOCK.get())));
    public static final DeferredBlock<Block> SCRAP_METAL_SLAB
            = regWithItem("scrap_metal_slab", () -> new SlabBlock(getProp(SCRAP_METAL_BLOCK.get())));

    public static final DeferredBlock<Block> ORNATE_SCRAP_METAL
            = regWithItem("ornate_scrap_metal", () -> new Block(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> ORNATE_SCRAP_METAL_STAIRS
            = regWithItem("ornate_scrap_metal_stairs", () -> new StairBlock(ORNATE_SCRAP_METAL.get().defaultBlockState(), getProp(ORNATE_SCRAP_METAL.get())));
    public static final DeferredBlock<Block> ORNATE_SCRAP_METAL_SLAB
            = regWithItem("ornate_scrap_metal_slab", () -> new SlabBlock(getProp(ORNATE_SCRAP_METAL.get())));

    public static final DeferredBlock<Block> SHS_PIPING
            = regWithItem("shs_piping", () -> new RotatedPillarBlock(getProp(Blocks.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> CHS_PIPING
            = regWithItem("chs_piping", () -> new RotatedPillarBlock(getProp(Blocks.NETHERITE_BLOCK)));

    // drywall
    public static final DeferredBlock<Block> DRYWALL
            = regWithItem("drywall", () -> new RotatedPillarBlock(BlockBehaviour.Properties.of().strength(0.5f).destroyTime(0.5f)));
    public static final DeferredBlock<Block> DRYWALL_STAIRS
            = regWithItem("drywall_stairs", () -> new StairBlock(DRYWALL.get().defaultBlockState(), getProp(DRYWALL.get())));
    public static final DeferredBlock<Block> DRYWALL_SLAB
            = regWithItem("drywall_slab", () -> new SlabBlock(getProp(DRYWALL.get())));

    // industrial grade concrete
    public static final DeferredBlock<Block> BLACK_INDUSTRY_CONCRETE
            = regWithItem("black_industry_concrete", () -> new Block(getProp(Blocks.BLACK_CONCRETE)));
    public static final DeferredBlock<Block> CARVED_BLACK_INDUSTRY_CONCRETE
            = regWithItem("carved_black_industry_concrete", () -> new Block(getProp(Blocks.BLACK_CONCRETE)));
    public static final DeferredBlock<Block> BLACK_INDUSTRY_CONCRETE_TILE
            = regWithItem("black_industry_concrete_tile", () -> new Block(getProp(Blocks.BLACK_CONCRETE)));

    public static final DeferredBlock<Block> WARM_INDUSTRY_CONCRETE
            = regWithItem("warm_industry_concrete", () -> new Block(getProp(Blocks.BLACK_CONCRETE)));

    public static final DeferredBlock<Block> COLD_INDUSTRY_CONCRETE
            = regWithItem("cold_industry_concrete", () -> new Block(getProp(Blocks.BLACK_CONCRETE)));

    // bricks
    public static final List<DeferredBlock<Block>> SCORCHED_BRICKS
            = reg("scorched_bricks", () -> new Block(getProp(Blocks.BRICKS)), 9);
    public static final List<DeferredBlock<Block>> SCORCHED_BRICK_STAIRS
            = reg("scorched_brick_stairs", () -> new StairBlock(getState(SCORCHED_BRICKS.getFirst()), getProp(SCORCHED_BRICKS.getFirst())), 9);
    public static final List<DeferredBlock<Block>> SCORCHED_BRICK_SLAB
            = reg("scorched_brick_slab", () -> new SlabBlock(getProp(SCORCHED_BRICKS.getFirst())), 9);
    public static final List<DeferredBlock<Block>> SCORCHED_BRICK_WALL
            = reg("scorched_brick_wall", () -> new WallBlock(getProp(SCORCHED_BRICKS.getFirst())), 9);

    public static final List<DeferredBlock<Block>> SCORCHED_TILE
            = reg("scorched_tile", () -> new Block(getProp(SCORCHED_BRICKS.getFirst())), 4);
    public static final List<DeferredBlock<Block>> SCORCHED_TILE_STAIRS
            = reg("scorched_tile_stairs", () -> new StairBlock(getState(SCORCHED_BRICKS.getFirst()), getProp(SCORCHED_BRICKS.getFirst())), 4);
    public static final List<DeferredBlock<Block>> SCORCHED_TILE_SLAB
            = reg("scorched_tile_slab", () -> new SlabBlock(getProp(SCORCHED_BRICKS.getFirst())), 4);
    public static final List<DeferredBlock<Block>> SCORCHED_TILE_WALL
            = reg("scorched_tile_wall", () -> new WallBlock(getProp(SCORCHED_BRICKS.getFirst())), 4);

    public static final DeferredBlock<Block> ASHY_SCORCHED_BRICKS
            = regWithItem("ashy_scorched_bricks", () -> new Block(getProp(Blocks.BRICKS)));

    public static final DeferredBlock<Block> ASHY_SCORCHED_BRICK_STAIRS
            = regWithItem("ashy_scorched_brick_stairs", () -> new StairBlock(getState(ASHY_SCORCHED_BRICKS) , getProp(ASHY_SCORCHED_BRICKS)));

    public static final DeferredBlock<Block> ASHY_SCORCHED_BRICK_SLAB
            = regWithItem("ashy_scorched_brick_slab", () -> new SlabBlock(getProp(ASHY_SCORCHED_BRICKS)));

    public static final DeferredBlock<Block> ASHY_SCORCHED_BRICK_WALL
            = regWithItem("ashy_scorched_brick_wall", () -> new WallBlock(getProp(ASHY_SCORCHED_BRICKS)));


    // vent


    //public static final DeferredBlock<Block> TEST
    //        = registerBlockItem("test", () -> new FauxMultiBlock<>(getProperties(Blocks.NETHERITE_BLOCK), TestMulti.class));

    // lamps

    public static final Supplier<?> SAFETY_LIGHT
            = regWithItem("safety_light", () -> new SafetyLightBlock(getProp(Blocks.NETHERITE_BLOCK)));

    public static final DeferredBlock<Block> FLUX_BULB
            = reg("flux_bulb", () -> new FluxBulbBlock(getProp(Blocks.NETHERITE_BLOCK), 7, DustParticleOptions.REDSTONE));

    public static final DeferredBlock<Block> INVERTED_FLUX_BULB
            = reg("inverted_flux_bulb", () -> new InvertedFluxBulbBlock(getProp(Blocks.NETHERITE_BLOCK), 7, DustParticleOptions.REDSTONE));

    // todo : dont want the uv bulbs to emit light at all but otherwise the emissiveness doesnt work ): fix when porting with the resourcepack feature
    public static final DeferredBlock<Block> UV_FLUX_BULB
            = reg("uv_flux_bulb", () -> new FluxBulbBlock(getProp(Blocks.NETHERITE_BLOCK), 1, ParticleTypes.GLOW));

    public static final DeferredBlock<Block> INVERTED_UV_FLUX_BULB
            = reg("inverted_uv_flux_bulb", () -> new InvertedFluxBulbBlock(getProp(Blocks.NETHERITE_BLOCK), 1, ParticleTypes.GLOW));

    public static final Dictionary<DyeColor, DeferredBlock<Block>> FLUX_BULBS
            = regDyeSet("%s_flux_bulb", () -> new FluxBulbBlock(getProp(Blocks.NETHERITE_BLOCK), 12, null), false);

    public static final Dictionary<DyeColor, DeferredBlock<Block>> INVERTED_FLUX_BULBS
            = regDyeSet("inverted_%s_flux_bulb", () -> new InvertedFluxBulbBlock(getProp(Blocks.NETHERITE_BLOCK), 12, null), false);

    // other


    public static final DeferredBlock<Block> DISPLAY
            = regWithItem("display", () -> new DisplayBlock(getProp(Blocks.NETHERITE_BLOCK)));


    /// register block and item
    @SuppressWarnings({"unchecked"})
    private static <T extends Block> DeferredBlock<T> regWithItem(String id, Supplier<? extends Block> blockType) {
        var tempBlock = BLOCKS.register(id, blockType);
        BLOCKITEMS.registerSimpleBlockItem(tempBlock);
        return (DeferredBlock<T>) tempBlock;
    }

    /// register blocks
    @SuppressWarnings({"unchecked"})
    private static <T extends Block> List<DeferredBlock<T>> reg(String id, Supplier<? extends Block> blockType, int amount) {
        List<DeferredBlock<T>> list = new ArrayList<>();
        for(int i = 0; i < amount; i++) {
            list.add((DeferredBlock<T>) BLOCKS.register(String.format("%s_%s", id, i + 1), blockType));
        }
        return list;
    }

    /// register block
    @SuppressWarnings({"unchecked"})
    private static <T extends Block> DeferredBlock<T> reg(String id, Supplier<? extends Block> blockType) {
        return (DeferredBlock<T>) BLOCKS.register(id, blockType);
    }

    /// register colour set of block and item
    @SuppressWarnings({"unchecked"})
    private static <T extends Block> Dictionary<DyeColor, DeferredBlock<T>> regDyeSet(String id, Supplier<? extends Block> blockType, boolean withItem) {
        Dictionary<DyeColor, DeferredBlock<T>> blocks = new Hashtable<>();

        for(DyeColor colour : DyeColor.values()) {
            String name = String.format(id, colour.getName());
            Supplier<? extends Block> block = withItem ? regWithItem(name, blockType) : reg(name, blockType);
            blocks.put(colour, (DeferredBlock<T>) block);
        }
        return blocks;
    }
    
    /// returns block properties
    private static BlockBehaviour.Properties getProp(Block block) {
        return BlockBehaviour.Properties.ofFullCopy(block);
    }

    private static BlockBehaviour.Properties getProp(DeferredBlock<Block> block) {
            return BlockBehaviour.Properties.ofFullCopy(block.get());
    }

    private static BlockState getState(DeferredBlock<Block> block) {
        return block.get().defaultBlockState();
    }
}
