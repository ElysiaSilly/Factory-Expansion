package dev.teamcitrusmods.thermal_fne.datagen.provider;

import dev.teamcitrusmods.thermal_fne.ThermalFNE;
import dev.teamcitrusmods.thermal_fne.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class FNEItemModelProvider extends ItemModelProvider {
    public FNEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ThermalFNE.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.BLACK_IRON_INGOT.get().asItem());
        basicItem(ItemRegistry.RED_COPPER_INGOT.get().asItem());

        wallInventory("slag_block_wall", new ResourceLocation("thermal", "block/slag_block"));
        wallInventory("slag_bricks_wall", new ResourceLocation("thermal", "block/slag_bricks"));
        wallInventory("rich_slag_block_wall", new ResourceLocation("thermal", "block/rich_slag_block"));
        wallInventory("rich_slag_bricks_wall", new ResourceLocation("thermal", "block/rich_slag_bricks"));
    }
}
