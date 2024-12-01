package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.item.PoppedDrywallFoodItem;
import com.teamcitrus.factory_expansion.core.properties.FEProperties;
import com.teamcitrus.factory_expansion.core.properties.properties.FluxBulbMode;
import com.teamcitrus.factory_expansion.common.item.cycleable.CycleBlockItem;
import com.teamcitrus.factory_expansion.common.item.cycleable.OptPropertyBlock;
import com.teamcitrus.factory_expansion.common.item.DrywallFoodItem;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import com.teamcitrus.factory_expansion.common.item.WrenchItem;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FEItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    public static final DeferredItem<FlamethrowerItem> FLAMETHROWER =
            ITEMS.register("flamethrower", () ->
                    new FlamethrowerItem(new Item.Properties()
                            .stacksTo(1)
                            // this might not be needed for now // .component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
                    ));

    public static final DeferredItem<WrenchItem> WRENCH =
            ITEMS.register("wrench", () ->
                    new WrenchItem(new Item.Properties()
                            .stacksTo(1))
            );

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


    public static final Supplier<Item> BLACK_ALLOY = ITEMS.registerSimpleItem("black_alloy", new Item.Properties());
    public static final Supplier<Item> PALE_ALLOY = ITEMS.registerSimpleItem("pale_alloy", new Item.Properties());
    public static final Supplier<Item> ARID_ALLOY = ITEMS.registerSimpleItem("arid_alloy", new Item.Properties());
    public static final Supplier<Item> BRASS_ALLOY = ITEMS.registerSimpleItem("brass_alloy", new Item.Properties());

    public static final Supplier<Item> SCRAP_METAL = ITEMS.registerSimpleItem("scrap_metal", new Item.Properties());

    /// CYCLE ITEMS

    public static final DeferredItem<CycleBlockItem> TRANS_BLOCK =
            ITEMS.register("transgender_block", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.RANDOM_AND_CYCLE,

                    optProperty(Blocks.LIGHT_BLUE_WOOL, 1).placementContext(),
                    optProperty(Blocks.WHITE_WOOL, 1).placementContext(),
                    optProperty(Blocks.PINK_WOOL, 1).placementContext()

                    )
            );

    public static final DeferredItem<CycleBlockItem> RANDOM_LOG =
            ITEMS.register("random_log", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.RANDOM_ONLY,

                    optProperty(Blocks.OAK_LOG, 1).setProperty(BlockStateProperties.AXIS, Direction.Axis.X),
                    optProperty(Blocks.OAK_LOG, 1).setProperty(BlockStateProperties.AXIS, Direction.Axis.Y),
                    optProperty(Blocks.OAK_LOG, 1).setProperty(BlockStateProperties.AXIS, Direction.Axis.Z)

                    )
            );

    public static final DeferredItem<CycleBlockItem> FLUX_BULB =
            ITEMS.register("flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.CYCLE_ONLY,

                    optProperty(FEBlocks.FLUX_BULB.get(), 1).placementContext(),
                    optProperty(FEBlocks.INVERTED_FLUX_BULB.get(), 1).placementContext(),
                    optProperty(FEBlocks.FLUX_BULB.get(), 1).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                    optProperty(FEBlocks.INVERTED_FLUX_BULB.get(), 1).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> UV_FLUX_BULB =
            ITEMS.register("uv_flux_bulb", () -> new CycleBlockItem(
                            new Item.Properties(),
                            CycleBlockItem.Mode.CYCLE_ONLY,

                            optProperty(FEBlocks.UV_FLUX_BULB.get(), 1).placementContext(),
                            optProperty(FEBlocks.INVERTED_UV_FLUX_BULB.get(), 1).placementContext(),
                            optProperty(FEBlocks.UV_FLUX_BULB.get(), 1).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                            optProperty(FEBlocks.INVERTED_UV_FLUX_BULB.get(), 1).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> RED_FLUX_BULB =
            ITEMS.register("red_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.CYCLE_ONLY,

                    optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.RED).get(), 1).placementContext(),
                    optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.RED).get(), 1).placementContext(),
                    optProperty(FEBlocks.FLUX_BULBS.get(DyeColor.RED).get(), 1).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext(),
                    optProperty(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.RED).get(), 1).setProperty(FEProperties.FLUX_BULB_MODE, FluxBulbMode.BLINKING).placementContext()

                    ).assignToItem()
            );

    public static final DeferredItem<CycleBlockItem> BLACK_INDUSTRIAL_VENT =
            ITEMS.register("black_industrial_vent", () -> new CycleBlockItem(
                    new Item.Properties(),
                    CycleBlockItem.Mode.CYCLE_ONLY,

                    optProperty(FEBlocks.SMALL_BLACK_VENT.get(), 1).placementContext(),
                    optProperty(FEBlocks.MEDIUM_BLACK_VENT.get(),4).placementContext(),
                    optProperty(FEBlocks.LARGE_BLACK_VENT.get(), 9).placementContext()

                    ).assignToItem()
            );

    /// create OptionalPropertyBlock

    public static OptPropertyBlock optProperty(Block block, int cost) {
        return new OptPropertyBlock(block, cost);
    }
}
