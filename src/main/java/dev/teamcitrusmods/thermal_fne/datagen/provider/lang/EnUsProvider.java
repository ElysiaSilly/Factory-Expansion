package dev.teamcitrusmods.thermal_fne.datagen.provider.lang;

import dev.teamcitrusmods.thermal_fne.ThermalFNE;
import dev.teamcitrusmods.thermal_fne.registry.BlockRegistry;
import dev.teamcitrusmods.thermal_fne.registry.CreativeModeTabRegistry;
import dev.teamcitrusmods.thermal_fne.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class EnUsProvider extends LanguageProvider {

    public EnUsProvider(PackOutput output, String locale) {
        super(output, ThermalFNE.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.thermal_fne", "Thermal: Factory Needs Expansion");

        add(ItemRegistry.BLACK_IRON_INGOT.get(), "Black Iron Ingot");
        add(ItemRegistry.RED_COPPER_INGOT.get(), "Red Copper Ingot");

        add(BlockRegistry.SLAG_BLOCK_SLAB.get(), "Slag Block Slab");
        add(BlockRegistry.SLAG_BLOCK_STAIRS.get(), "Slag Block Stairs");
        add(BlockRegistry.SLAG_BLOCK_WALL.get(), "Slag Block Wall");
        add(BlockRegistry.SLAG_BRICKS_SLAB.get(), "Slag Bricks Slab");
        add(BlockRegistry.SLAG_BRICKS_STAIRS.get(), "Slag Bricks Stairs");
        add(BlockRegistry.SLAG_BRICKS_WALL.get(), "Slag Bricks Wall");
        add(BlockRegistry.RICH_SLAG_BLOCK_SLAB.get(), "Rich Slag Block Slab");
        add(BlockRegistry.RICH_SLAG_BLOCK_STAIRS.get(), "Rich Slag Block Stairs");
        add(BlockRegistry.RICH_SLAG_BLOCK_WALL.get(), "Rich Slag Block Wall");
        add(BlockRegistry.RICH_SLAG_BRICKS_SLAB.get(), "Rich Slag Bricks Slab");
        add(BlockRegistry.RICH_SLAG_BRICKS_STAIRS.get(), "Rich Slag Bricks Stairs");
        add(BlockRegistry.RICH_SLAG_BRICKS_WALL.get(), "Rich Slag Bricks Wall");

        add(BlockRegistry.FLUX_LAMP.get(), "Flux Lamp");
        add(BlockRegistry.FLUX_LAMP_INVERTED.get(), "Flux Lamp (Inverted)");
    }
}