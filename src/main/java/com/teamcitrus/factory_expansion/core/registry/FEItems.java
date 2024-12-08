package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.block.GirderBlock;
import com.teamcitrus.factory_expansion.common.item.DrywallFoodItem;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import com.teamcitrus.factory_expansion.common.item.PoppedDrywallFoodItem;
import com.teamcitrus.factory_expansion.common.item.WrenchItem;
import com.teamcitrus.factory_expansion.common.item.cycleable.CycleBlockItem;
import com.teamcitrus.factory_expansion.common.item.cycleable.OptPropertyBlock;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.keys.FELocations;
import com.teamcitrus.factory_expansion.core.properties.FEProperties;
import com.teamcitrus.factory_expansion.core.properties.properties.FluxBulbMode;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@SuppressWarnings({"unchecked"}) // yeah i should prolly fix the generics stuff lol
public class FEItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    public static final DeferredItem<FlamethrowerItem> FLAMETHROWER =
            ITEMS.register("flamethrower", FlamethrowerItem::new);

    public static final DeferredItem<WrenchItem> WRENCH =
            ITEMS.register("wrench", WrenchItem::new);



    /// OTHER

    public static final DeferredItem<DrywallFoodItem> DRYWALL_RATION =
            ITEMS.register("drywall_ration", () ->
                    new DrywallFoodItem(new Item.Properties()
                            .stacksTo(32))
            );

    public static final DeferredItem<PoppedDrywallFoodItem> POPPED_DRYWALL_RATION =
            ITEMS.register("popped_drywall_ration", () ->
                    new PoppedDrywallFoodItem(new Item.Properties()
                            .stacksTo(1)
                            .durability(8)
                            .food(new FoodProperties.Builder()
                                    .alwaysEdible()
                                    .fast()
                                    .nutrition(1)
                                    .saturationModifier(1)
                                    .build()
                            ))
            );


    public static final DeferredItem<Item> BLACK_ALLOY
            = ITEMS.registerSimpleItem("black_alloy", new Item.Properties());
    public static final DeferredItem<Item> PALE_ALLOY
            = ITEMS.registerSimpleItem("pale_alloy", new Item.Properties());
    public static final DeferredItem<Item> ARID_ALLOY
            = ITEMS.registerSimpleItem("arid_alloy", new Item.Properties());

    public static final DeferredItem<Item> SCRAP_METAL
            = ITEMS.registerSimpleItem("scrap_metal", new Item.Properties());

    public static final DeferredItem<Item> SCORCHED_BRICK
            = ITEMS.registerSimpleItem("scorched_brick", new Item.Properties());

    /// CYCLE ITEMS

    public static final DeferredItem<CycleBlockItem> TRANS_BLOCK =
            ITEMS.register("transgender_block", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                    optProperty(Blocks.LIGHT_BLUE_WOOL, 1, FELocations.gui.TEMP).placementContext(),
                    optProperty(Blocks.WHITE_WOOL, 1, FELocations.gui.TEMP).placementContext(),
                    optProperty(Blocks.PINK_WOOL, 1, FELocations.gui.TEMP).placementContext()

                    )
            );

    public static final DeferredItem<CycleBlockItem> BLACK_GIRDER =
            ITEMS.register("black_girder", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.BLACK_GIRDER.get(), 1, FELocations.gui.CYCLE_GIRDER)
                                    .placementContext(),
                            optProperty(FEBlocks.BLACK_GIRDER.get(), 1, FELocations.gui.CYCLE_GIRDER_VERTICAL)
                                    .setProperty(GirderBlock.Y_AXIS, true)
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> PALE_GIRDER =
            ITEMS.register("pale_girder", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.PALE_GIRDER.get(), 1, FELocations.gui.CYCLE_GIRDER)
                                    .placementContext(),
                            optProperty(FEBlocks.PALE_GIRDER.get(), 1, FELocations.gui.CYCLE_GIRDER_VERTICAL)
                                    .setProperty(GirderBlock.Y_AXIS, true)
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> ARID_GIRDER =
            ITEMS.register("arid_girder", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.ARID_GIRDER.get(), 1, FELocations.gui.CYCLE_GIRDER)
                                    .placementContext(),
                            optProperty(FEBlocks.ARID_GIRDER.get(), 1, FELocations.gui.CYCLE_GIRDER_VERTICAL)
                                    .setProperty(GirderBlock.Y_AXIS, true)
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> FLUX_BULB =
            ITEMS.register("flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.CYCLE_ONLY,

                    optProperty(FEBlocks.FLUX_BULB.get(), 1, FELocations.gui.CYCLE_BULB_NORMAL)
                            .placementContext(),
                    optProperty(FEBlocks.INVERTED_FLUX_BULB.get(), 1, FELocations.gui.CYCLE_BULB_INVERTED)
                            .placementContext(),
                    optProperty(FEBlocks.FLUX_BULB.get(), 1, FELocations.gui.CYCLE_BULB_BLINKING)
                            .setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).
                            placementContext(),
                    optProperty(FEBlocks.INVERTED_FLUX_BULB.get(), 1, FELocations.gui.CYCLE_BULB_BLINKING_INVERTED)
                            .setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)
                            .placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> UV_FLUX_BULB =
            ITEMS.register("uv_flux_bulb", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.UV_FLUX_BULB.get(), 1, FELocations.gui.CYCLE_BULB_NORMAL)
                                    .placementContext(),
                            optProperty(FEBlocks.INVERTED_UV_FLUX_BULB.get(), 1, FELocations.gui.CYCLE_BULB_INVERTED)
                                    .placementContext(),
                            optProperty(FEBlocks.UV_FLUX_BULB.get(), 1, FELocations.gui.CYCLE_BULB_BLINKING)
                                    .setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)
                                    .placementContext(),
                            optProperty(FEBlocks.INVERTED_UV_FLUX_BULB.get(), 1, FELocations.gui.CYCLE_BULB_BLINKING_INVERTED)
                                    .setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)
                                    .placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> WHITE_FLUX_BULB =
            ITEMS.register("white_flux_bulb", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.WHITE).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.WHITE).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.WHITE).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.WHITE).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> ORANGE_FLUX_BULB =
            ITEMS.register("orange_flux_bulb", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.ORANGE).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.ORANGE).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.ORANGE).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.ORANGE).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> MAGENTA_FLUX_BULB =
            ITEMS.register("magenta_flux_bulb", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.MAGENTA).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.MAGENTA).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.MAGENTA).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.MAGENTA).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> LIGHT_BLUE_FLUX_BULB =
            ITEMS.register("light_blue_flux_bulb", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.LIGHT_BLUE).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.LIGHT_BLUE).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.LIGHT_BLUE).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.LIGHT_BLUE).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> YELLOW_FLUX_BULB =
            ITEMS.register("yellow_flux_bulb", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.YELLOW).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.YELLOW).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.YELLOW).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.YELLOW).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> LIME_FLUX_BULB =
            ITEMS.register("lime_flux_bulb", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.LIME).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.LIME).get(), 1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.LIME).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                            optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.LIME).get(), 1, FELocations.gui.TEMP).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> RED_FLUX_BULB =
            ITEMS.register("red_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.CYCLE_ONLY,

                    optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.RED).get(), 1, FELocations.gui.CYCLE_BULB_NORMAL)
                            .placementContext(),
                    optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.RED).get(), 1, FELocations.gui.CYCLE_BULB_INVERTED)
                            .placementContext(),
                    optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.RED).get(), 1, FELocations.gui.CYCLE_BULB_BLINKING)
                            .setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)
                            .placementContext(),
                    optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.RED).get(), 1, FELocations.gui.CYCLE_BULB_BLINKING_INVERTED)
                            .setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)
                            .placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> BLACK_INDUSTRIAL_VENT =
            ITEMS.register("black_industrial_vent", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.CYCLE_ONLY,

                    optProperty(FEBlocks.SMALL_BLACK_VENT.get(), 1, FELocations.gui.CYCLE_BLOCK)
                            .placementContext(),
                    optProperty(FEBlocks.MEDIUM_BLACK_VENT.get(),4, FELocations.gui.CYCLE_BLOCK_2X2)
                            .placementContext(),
                    optProperty(FEBlocks.LARGE_BLACK_VENT.get(), 9, FELocations.gui.CYCLE_BLOCK_3X3)
                            .placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> BLACK_CATWALK =
            ITEMS.register("black_catwalk", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.BLACK_CATWALK.get(), 1, FELocations.gui.CYCLE_SLAB)
                                    .placementContext(),
                            /*optProperty(FEBlocks.BLACK_CATWALK.get(),2, FELocations.gui.CYCLE_BLOCK)
                                    .setProperty(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)
                                    .placementContext(),*/
                            optProperty(FEBlocks.BLACK_CATWALK_STAIRS.get(), 1, FELocations.gui.CYCLE_STAIRS)
                                    .placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_TILE =
            ITEMS.register("scorched_tile", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                    optProperty(FEBlocks.SCORCHED_TILE.get(0).get(),1, FELocations.gui.TEMP).placementContext(),
                    optProperty(FEBlocks.SCORCHED_TILE.get(1).get(),1, FELocations.gui.TEMP).placementContext(),
                    optProperty(FEBlocks.SCORCHED_TILE.get(2).get(),1, FELocations.gui.TEMP).placementContext(),
                    optProperty(FEBlocks.SCORCHED_TILE.get(3).get(),1, FELocations.gui.TEMP).placementContext()
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_TILE_STAIRS =
            ITEMS.register("scorched_tile_stairs", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                            optProperty(FEBlocks.SCORCHED_TILE_STAIRS.get(0).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_STAIRS.get(1).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_STAIRS.get(2).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_STAIRS.get(3).get(),1, FELocations.gui.TEMP).placementContext()
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_TILE_SLAB =
            ITEMS.register("scorched_tile_slab", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                            optProperty(FEBlocks.SCORCHED_TILE_SLAB.get(0).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_SLAB.get(1).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_SLAB.get(2).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_SLAB.get(3).get(),1, FELocations.gui.TEMP).placementContext()
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_TILE_WALL =
            ITEMS.register("scorched_tile_wall", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                            optProperty(FEBlocks.SCORCHED_TILE_WALL.get(0).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_WALL.get(1).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_WALL.get(2).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_TILE_WALL.get(3).get(),1, FELocations.gui.TEMP).placementContext()
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_BRICKS =
            ITEMS.register("scorched_bricks", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.RANDOM_ONLY,

                            optProperty(FEBlocks.SCORCHED_BRICKS.get(0).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICKS.get(1).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICKS.get(2).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICKS.get(3).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICKS.get(4).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICKS.get(5).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICKS.get(6).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICKS.get(7).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICKS.get(8).get(),1, FELocations.gui.TEMP).placementContext()
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_BRICK_STAIRS =
            ITEMS.register("scorched_brick_stairs", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.RANDOM_ONLY,

                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(0).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(1).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(2).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(3).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(4).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(5).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(6).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(7).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_STAIRS.get(8).get(),1, FELocations.gui.TEMP).placementContext()
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_BRICK_SLAB =
            ITEMS.register("scorched_brick_slab", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.RANDOM_ONLY,

                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(0).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(1).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(2).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(3).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(4).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(5).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(6).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(7).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_SLAB.get(8).get(),1, FELocations.gui.TEMP).placementContext()
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_BRICK_WALL =
            ITEMS.register("scorched_brick_wall", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.RANDOM_ONLY,

                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(0).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(1).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(2).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(3).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(4).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(5).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(6).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(7).get(),1, FELocations.gui.TEMP).placementContext(),
                            optProperty(FEBlocks.SCORCHED_BRICK_WALL.get(8).get(),1, FELocations.gui.TEMP).placementContext()
                    ).assignToItem()
            );

    /// create OptionalPropertyBlock

    public static OptPropertyBlock optProperty(Block block, int cost, ResourceLocation location) {
        return new OptPropertyBlock(block, cost, location);
    }
}
