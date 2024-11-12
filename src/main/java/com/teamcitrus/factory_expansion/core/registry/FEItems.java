package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.item.CycleBlockItem.CycleBlockItem;
import com.teamcitrus.factory_expansion.common.item.CycleBlockItem.CycleMode;
import com.teamcitrus.factory_expansion.common.item.CycleBlockItem.OptionalStateBlock;
import com.teamcitrus.factory_expansion.common.item.DrywallRationItem;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import com.teamcitrus.factory_expansion.common.item.PoppedDrywallRationItem;
import com.teamcitrus.factory_expansion.common.item.WrenchItem;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemContainerContents;
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
                            .component(DataComponents.CONTAINER, ItemContainerContents.EMPTY)
                    ));

    public static final DeferredItem<WrenchItem> WRENCH =
            ITEMS.register("wrench", () ->
                    new WrenchItem(new Item.Properties()
                            .stacksTo(1))
            );

    /// CYCLE ITEMS

    public static final DeferredItem<CycleBlockItem> TRANS_BLOCK =
            ITEMS.register("transgender_block", () -> new CycleBlockItem(
                    new Item.Properties(),

                    new OptionalStateBlock(Blocks.LIGHT_BLUE_WOOL).placementContext(),
                    new OptionalStateBlock(Blocks.WHITE_WOOL).placementContext(),
                    new OptionalStateBlock(Blocks.PINK_WOOL).placementContext()

                    ).mode(CycleMode.RANDOM_AND_CYCLE)
            );

    public static final DeferredItem<CycleBlockItem> RANDOM_LOG =
            ITEMS.register("random_log", () -> new CycleBlockItem(
                    new Item.Properties(),

                    new OptionalStateBlock(Blocks.OAK_LOG).setState(BlockStateProperties.AXIS, Direction.Axis.X),
                    new OptionalStateBlock(Blocks.OAK_LOG).setState(BlockStateProperties.AXIS, Direction.Axis.Y),
                    new OptionalStateBlock(Blocks.OAK_LOG).setState(BlockStateProperties.AXIS, Direction.Axis.Z)

                    ).mode(CycleMode.RANDOM_ONLY)
            );

    public static final DeferredItem<CycleBlockItem> FLUX_BULB =
            ITEMS.register("flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(),

                    new OptionalStateBlock(FEBlocks.FLUX_BULB.get()).placementContext(),
                    new OptionalStateBlock(FEBlocks.INVERTED_FLUX_BULB.get()).placementContext()

                    ).assignToItem().mode(CycleMode.CYCLE_ONLY)
            );

    public static final DeferredItem<CycleBlockItem> RED_FLUX_BULB =
            ITEMS.register("red_flux_bulb", () -> new CycleBlockItem(
                    new Item.Properties(),

                    new OptionalStateBlock(FEBlocks.FLUX_BULBS.get(DyeColor.RED).get()).placementContext(),
                    new OptionalStateBlock(FEBlocks.INVERTED_FLUX_BULBS.get(DyeColor.RED).get()).placementContext()

                    ).assignToItem().mode(CycleMode.CYCLE_ONLY)
            );

    public static final DeferredItem<CycleBlockItem> INDUSTRIAL_VENT =
            ITEMS.register("industrial_vent", () -> new CycleBlockItem(
                    new Item.Properties(),

                    new OptionalStateBlock(FEBlocks.LARGE_INDUSTRIAL_VENT.get()).placementContext(),
                    new OptionalStateBlock(FEBlocks.MEDIUM_INDUSTRIAL_VENT.get()).placementContext()

                    ).assignToItem().mode(CycleMode.CYCLE_ONLY)
            );

    /// OTHER

    public static final DeferredItem<DrywallRationItem> DRYWALL_RATION =
            ITEMS.register("drywall_ration", () ->
                    new DrywallRationItem(new Item.Properties()
                            .stacksTo(32))
            );

    public static final DeferredItem<PoppedDrywallRationItem> POPPED_DRYWALL_RATION =
            ITEMS.register("popped_drywall_ration", () ->
                    new PoppedDrywallRationItem(new Item.Properties()
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

}
