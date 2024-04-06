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
    public static final RegistryObject<Item> BLACK_IRON_INGOT = register("black_iron_ingot", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RED_COPPER_INGOT = register("red_copper_ingot", () -> new Item(new Item.Properties()));

    public static RegistryObject<Item> register(String name, Supplier<Item> supplier) {
        return ITEMS.register(name, supplier);
    }
}
