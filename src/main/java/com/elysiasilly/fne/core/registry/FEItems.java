package com.elysiasilly.fne.core.registry;

import com.elysiasilly.babel.api.common.item.cycleable.CycleBlockItem;
import com.elysiasilly.babel.api.common.item.cycleable.PredefinedBlockState;
import com.elysiasilly.fne.common.block.GirderBlock;
import com.elysiasilly.fne.common.item.*;
import com.elysiasilly.fne.FactoExpa;
import com.elysiasilly.fne.core.properties.FEProperties;
import com.elysiasilly.fne.core.properties.properties.FluxBulbMode;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FEItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    public static final DeferredItem<FlamethrowerItem> FLAMETHROWER =
            ITEMS.register("flamethrower", FlamethrowerItem::new);

    public static final DeferredItem<WrenchItem> WRENCH =
            ITEMS.register("wrench", WrenchItem::new);

    public static final DeferredItem<SkinTokenItem> SKIN_TOKEN =
            ITEMS.register("skin_token", SkinTokenItem::new);

    /// OTHER

    public static final DeferredItem<DrywallFoodItem> DRYWALL_RATION =
            ITEMS.register("drywall_ration", () ->
                    new DrywallFoodItem(new Item.Properties()
                            .stacksTo(32))
            );

    public static final DeferredItem<PoppedDrywallFoodItem> POPPED_DRYWALL_RATION =
            ITEMS.register("popped_drywall_ration", () ->
                    new PoppedDrywallFoodItem(new Item.Properties().stacksTo(1).durability(8)
                            .food(new FoodProperties.Builder()
                                    .alwaysEdible()
                                    .fast()
                                    .nutrition(1)
                                    .saturationModifier(1)
                                    .build()
                            )
                    )
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

    public static final DeferredItem<CycleBlockItem> BLACK_GIRDER =
            ITEMS.register("black_girder", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.BLACK_GIRDER),
                    create(FEBlocks.BLACK_GIRDER).set(GirderBlock.Y_AXIS, true)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> PALE_GIRDER =
            ITEMS.register("pale_girder", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.PALE_GIRDER),
                    create(FEBlocks.PALE_GIRDER).set(GirderBlock.Y_AXIS, true)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> ARID_GIRDER =
            ITEMS.register("arid_girder", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.ARID_GIRDER),
                    create(FEBlocks.ARID_GIRDER).set(GirderBlock.Y_AXIS, true)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> FLUX_BULB =
            ITEMS.register("flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.FLUX_BULB),
                    create(FEBlocks.INVERTED_FLUX_BULB),
                    create(FEBlocks.FLUX_BULB).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_FLUX_BULB).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> UV_FLUX_BULB =
            ITEMS.register("uv_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.UV_FLUX_BULB),
                    create(FEBlocks.INVERTED_UV_FLUX_BULB),
                    create(FEBlocks.UV_FLUX_BULB).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_UV_FLUX_BULB).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> WHITE_FLUX_BULB =
            ITEMS.register("white_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.FLUX_BULBS.get(DyeColor.WHITE)),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.WHITE)),
                    create(FEBlocks.FLUX_BULBS.get(DyeColor.WHITE)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.WHITE)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> ORANGE_FLUX_BULB =
            ITEMS.register("orange_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.FLUX_BULBS.get(DyeColor.ORANGE)),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.ORANGE)),
                    create(FEBlocks.FLUX_BULBS.get(DyeColor.ORANGE)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.ORANGE)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> MAGENTA_FLUX_BULB =
            ITEMS.register("magenta_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.FLUX_BULBS.get(DyeColor.MAGENTA)),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.MAGENTA)),
                    create(FEBlocks.FLUX_BULBS.get(DyeColor.MAGENTA)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.MAGENTA)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> LIGHT_BLUE_FLUX_BULB =
            ITEMS.register("light_blue_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.FLUX_BULBS.get(DyeColor.LIGHT_BLUE)),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.LIGHT_BLUE)),
                    create(FEBlocks.FLUX_BULBS.get(DyeColor.LIGHT_BLUE)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.LIGHT_BLUE)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> YELLOW_FLUX_BULB =
            ITEMS.register("yellow_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.FLUX_BULBS.get(DyeColor.YELLOW)),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.YELLOW)),
                    create(FEBlocks.FLUX_BULBS.get(DyeColor.YELLOW)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.YELLOW)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> LIME_FLUX_BULB =
            ITEMS.register("lime_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.FLUX_BULBS.get(DyeColor.LIME)),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.LIME)),
                    create(FEBlocks.FLUX_BULBS.get(DyeColor.LIME)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.LIME)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> RED_FLUX_BULB =
            ITEMS.register("red_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.FLUX_BULBS.get(DyeColor.RED)),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.RED)),
                    create(FEBlocks.FLUX_BULBS.get(DyeColor.RED)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING),
                    create(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.RED)).set(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> BLACK_INDUSTRIAL_VENT =
            ITEMS.register("black_industrial_vent", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.SMALL_BLACK_VENT),
                    create(FEBlocks.MEDIUM_BLACK_VENT).cost(4),
                    create(FEBlocks.LARGE_BLACK_VENT).cost(9)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> BLACK_CATWALK =
            ITEMS.register("black_catwalk", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.CYCLE_ONLY,

                    create(FEBlocks.BLACK_CATWALK),
                            /*optProperty(FEBlocks.BLACK_CATWALK,2, FELocations.gui.CYCLE_BLOCK)
                                    .set(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE)
                                    ,*/
                    create(FEBlocks.BLACK_CATWALK_STAIRS)

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_TILE =
            ITEMS.register("scorched_tile", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                    create(FEBlocks.SCORCHED_TILE.get(0)),
                    create(FEBlocks.SCORCHED_TILE.get(1)),
                    create(FEBlocks.SCORCHED_TILE.get(2)),
                    create(FEBlocks.SCORCHED_TILE.get(3))
                    
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_TILE_STAIRS =
            ITEMS.register("scorched_tile_stairs", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                    create(FEBlocks.SCORCHED_TILE_STAIRS.get(0)),
                    create(FEBlocks.SCORCHED_TILE_STAIRS.get(1)),
                    create(FEBlocks.SCORCHED_TILE_STAIRS.get(2)),
                    create(FEBlocks.SCORCHED_TILE_STAIRS.get(3))
                    
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_TILE_SLAB =
            ITEMS.register("scorched_tile_slab", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                    create(FEBlocks.SCORCHED_TILE_SLAB.get(0)),
                    create(FEBlocks.SCORCHED_TILE_SLAB.get(1)),
                    create(FEBlocks.SCORCHED_TILE_SLAB.get(2)),
                    create(FEBlocks.SCORCHED_TILE_SLAB.get(3))
                    
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_TILE_WALL =
            ITEMS.register("scorched_tile_wall", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                    create(FEBlocks.SCORCHED_TILE_WALL.get(0)),
                    create(FEBlocks.SCORCHED_TILE_WALL.get(1)),
                    create(FEBlocks.SCORCHED_TILE_WALL.get(2)),
                    create(FEBlocks.SCORCHED_TILE_WALL.get(3))
                    
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_BRICKS =
            ITEMS.register("scorched_bricks", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.RANDOM_ONLY,

                    create(FEBlocks.SCORCHED_BRICKS.get(0)),
                    create(FEBlocks.SCORCHED_BRICKS.get(1)),
                    create(FEBlocks.SCORCHED_BRICKS.get(2)),
                    create(FEBlocks.SCORCHED_BRICKS.get(3)),
                    create(FEBlocks.SCORCHED_BRICKS.get(4)),
                    create(FEBlocks.SCORCHED_BRICKS.get(5)),
                    create(FEBlocks.SCORCHED_BRICKS.get(6)),
                    create(FEBlocks.SCORCHED_BRICKS.get(7)),
                    create(FEBlocks.SCORCHED_BRICKS.get(8))
                    
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_BRICK_STAIRS =
            ITEMS.register("scorched_brick_stairs", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.RANDOM_ONLY,

                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(0)),
                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(1)),
                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(2)),
                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(3)),
                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(4)),
                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(5)),
                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(6)),
                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(7)),
                    create(FEBlocks.SCORCHED_BRICK_STAIRS.get(8))
                    
                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_BRICK_SLAB =
            ITEMS.register("scorched_brick_slab", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.RANDOM_ONLY,

                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(0)),
                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(1)),
                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(2)),
                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(3)),
                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(4)),
                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(5)),
                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(6)),
                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(7)),
                    create(FEBlocks.SCORCHED_BRICK_SLAB.get(8))

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> SCORCHED_BRICK_WALL =
            ITEMS.register("scorched_brick_wall", () -> new CycleBlockItem(
                    new Item.Properties(), CycleBlockItem.Mode.RANDOM_ONLY,

                    create(FEBlocks.SCORCHED_BRICK_WALL.get(0)),
                    create(FEBlocks.SCORCHED_BRICK_WALL.get(1)),
                    create(FEBlocks.SCORCHED_BRICK_WALL.get(2)),
                    create(FEBlocks.SCORCHED_BRICK_WALL.get(3)),
                    create(FEBlocks.SCORCHED_BRICK_WALL.get(4)),
                    create(FEBlocks.SCORCHED_BRICK_WALL.get(5)),
                    create(FEBlocks.SCORCHED_BRICK_WALL.get(6)),
                    create(FEBlocks.SCORCHED_BRICK_WALL.get(7)),
                    create(FEBlocks.SCORCHED_BRICK_WALL.get(8))

                    ).assignToItem()
            );

    /// create OptionalPropertyBlock

    public static PredefinedBlockState create(Block block) {
        return new PredefinedBlockState(block);
    }

    public static PredefinedBlockState create(DeferredBlock<?> block) {
        return create(block.get());
    }
}
