package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.item.CycleableBlockItem;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import com.teamcitrus.factory_expansion.common.item.WrenchItem;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.block.Blocks;
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

    public static final DeferredItem<CycleableBlockItem> TRANS_BLOCK =
            ITEMS.register("transgender_block", () ->
                    new CycleableBlockItem(
                            new Item.Properties(),
                            true,
                            Blocks.LIGHT_BLUE_WOOL, Blocks.WHITE_WOOL, Blocks.PINK_WOOL)
            );

    public static final DeferredItem<CycleableBlockItem> JUNK_COLLECTION =
            ITEMS.register("junk_collection", () ->
                    new CycleableBlockItem(
                            new Item.Properties(),
                            false,
                            Blocks.DEEPSLATE, Blocks.POLISHED_ANDESITE, Blocks.GRANITE, Blocks.DIORITE_SLAB, Blocks.ACACIA_WOOD, Blocks.SPAWNER, FEBlocks.INDUSTRIAL_VENT.get())
            );

    public static final Supplier<Item> BLACK_ALLOY = ITEMS.registerSimpleItem("black_alloy", new Item.Properties());
    public static final Supplier<Item> PALE_ALLOY = ITEMS.registerSimpleItem("pale_alloy", new Item.Properties());
    public static final Supplier<Item> ARID_ALLOY = ITEMS.registerSimpleItem("arid_alloy", new Item.Properties());
    public static final Supplier<Item> BRASS_ALLOY = ITEMS.registerSimpleItem("brass_alloy", new Item.Properties());

}
