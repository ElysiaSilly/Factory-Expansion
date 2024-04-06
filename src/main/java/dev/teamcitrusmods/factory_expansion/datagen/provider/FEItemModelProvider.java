package dev.teamcitrusmods.factory_expansion.datagen.provider;

import dev.teamcitrusmods.factory_expansion.FactoryExpansion;
import dev.teamcitrusmods.factory_expansion.registry.BlockRegistry;
import dev.teamcitrusmods.factory_expansion.registry.ItemRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.ModList;

public class FEItemModelProvider extends ItemModelProvider {
    public FEItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FactoryExpansion.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.BLACK_IRON_INGOT.get().asItem());
        basicItem(ItemRegistry.RED_COPPER_INGOT.get().asItem());

        if(ModList.get().isLoaded("thermal")) {
            wallInventory("slag_block_wall", new ResourceLocation("thermal", "block/slag_block"));
            wallInventory("slag_bricks_wall", new ResourceLocation("thermal", "block/slag_bricks"));
            wallInventory("rich_slag_block_wall", new ResourceLocation("thermal", "block/rich_slag_block"));
            wallInventory("rich_slag_bricks_wall", new ResourceLocation("thermal", "block/rich_slag_bricks"));
        }
    }
}
