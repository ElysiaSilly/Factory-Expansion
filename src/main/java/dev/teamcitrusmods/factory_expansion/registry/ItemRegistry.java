package dev.teamcitrusmods.factory_expansion.registry;

import dev.teamcitrusmods.factory_expansion.FactoryExpansion;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FactoryExpansion.MODID);

    // ITEMS
    public static final RegistryObject<Item> ALLOY1_INGOT = register("alloy1_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALLOY2_INGOT = register("alloy2_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALLOY3_INGOT = register("alloy3_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALLOY4_INGOT = register("alloy4_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> COPPER_NUGGET = register("copper_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALLOY3_NUGGET = register("alloy3_nugget", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> ALLOY4_NUGGET = register("alloy4_nugget", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> REDSTONE_LIGHTBULB = register("redstone_lightbulb", () -> new Item(new Item.Properties()));

    public static RegistryObject<Item> register(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }
}
