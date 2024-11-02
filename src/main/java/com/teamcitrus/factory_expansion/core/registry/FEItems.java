package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FEItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FactoExpa.MODID);

    public static final DeferredItem<FlamethrowerItem> FLAMETHROWER =
            ITEMS.register("flamethrower", () ->
                    new FlamethrowerItem(new Item.Properties()
                            .stacksTo(1))
            );

    public static final Supplier<Item> VANTA_ALLOY = ITEMS.registerSimpleItem("vanta_alloy", new Item.Properties());
    public static final Supplier<Item> PALE_ALLOY = ITEMS.registerSimpleItem("pale_alloy", new Item.Properties());
    public static final Supplier<Item> DESERT_ALLOY = ITEMS.registerSimpleItem("desert_alloy", new Item.Properties());
    public static final Supplier<Item> BRASS_ALLOY = ITEMS.registerSimpleItem("brass_alloy", new Item.Properties());

}
