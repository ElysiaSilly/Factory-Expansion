package dev.teamcitrusmods.thermal_fne.datagen.provider;

import dev.teamcitrusmods.thermal_fne.ThermalFNE;
import dev.teamcitrusmods.thermal_fne.registry.BlockRegistry;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class FNETagProvider {
    public static class FNEBlockTags extends BlockTagsProvider {

        public FNEBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, ThermalFNE.MODID, existingFileHelper);
        }

        @Override
        protected void addTags(HolderLookup.Provider pProvider) {
            tag(BlockTags.STAIRS).add(BlockRegistry.SLAG_BLOCK_SLAB.get());
            tag(BlockTags.SLABS).add(BlockRegistry.SLAG_BLOCK_STAIRS.get());
            tag(BlockTags.WALLS).add(BlockRegistry.SLAG_BLOCK_WALL.get());
            tag(BlockTags.STAIRS).add(BlockRegistry.SLAG_BRICKS_SLAB.get());
            tag(BlockTags.SLABS).add(BlockRegistry.SLAG_BRICKS_STAIRS.get());
            tag(BlockTags.WALLS).add(BlockRegistry.SLAG_BRICKS_WALL.get());
            tag(BlockTags.STAIRS).add(BlockRegistry.RICH_SLAG_BLOCK_SLAB.get());
            tag(BlockTags.SLABS).add(BlockRegistry.RICH_SLAG_BLOCK_STAIRS.get());
            tag(BlockTags.WALLS).add(BlockRegistry.RICH_SLAG_BLOCK_WALL.get());
            tag(BlockTags.STAIRS).add(BlockRegistry.RICH_SLAG_BRICKS_SLAB.get());
            tag(BlockTags.SLABS).add(BlockRegistry.RICH_SLAG_BRICKS_STAIRS.get());
            tag(BlockTags.WALLS).add(BlockRegistry.RICH_SLAG_BRICKS_WALL.get());

            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    BlockRegistry.SLAG_BLOCK_SLAB.get(), BlockRegistry.SLAG_BLOCK_STAIRS.get(), BlockRegistry.SLAG_BLOCK_WALL.get(),
                    BlockRegistry.RICH_SLAG_BLOCK_SLAB.get(), BlockRegistry.RICH_SLAG_BLOCK_STAIRS.get(), BlockRegistry.RICH_SLAG_BLOCK_WALL.get()
            );
        }
    }
}
