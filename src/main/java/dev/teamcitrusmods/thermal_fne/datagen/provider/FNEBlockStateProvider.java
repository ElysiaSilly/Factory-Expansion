package dev.teamcitrusmods.thermal_fne.datagen.provider;

import cofh.thermal.core.ThermalCore;
import dev.teamcitrusmods.thermal_fne.ThermalFNE;
import dev.teamcitrusmods.thermal_fne.registry.BlockRegistry;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cofh.thermal.lib.util.ThermalIDs.*;
import static cofh.thermal.lib.util.ThermalIDs.ID_RICH_SLAG_BRICKS;

public class FNEBlockStateProvider extends BlockStateProvider {
    private static final ExistingFileHelper.ResourceType MODEL = new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".json", "models");
    private static final ExistingFileHelper.ResourceType TEXTURE = new ExistingFileHelper.ResourceType(PackType.CLIENT_RESOURCES, ".png", "textures");
    private final ExistingFileHelper existingFileHelper;
    public FNEBlockStateProvider(PackOutput gen, ExistingFileHelper exFileHelper) {
        super(gen, ThermalFNE.MODID, exFileHelper);
        this.existingFileHelper = exFileHelper;
    }

    @Override
    protected void registerStatesAndModels() {
        slabBlockUncheckedWithItem(BlockRegistry.SLAG_BLOCK_SLAB.get(), blockTexture(ThermalCore.BLOCKS.get(ID_SLAG_BLOCK)), new ResourceLocation("thermal", "block/slag_block"));
        stairsBlockUncheckedWithItem(BlockRegistry.SLAG_BLOCK_STAIRS.get(), new ResourceLocation("thermal", "block/slag_block"));
        wallBlockUncheckedWithItem(BlockRegistry.SLAG_BLOCK_WALL.get(), new ResourceLocation("thermal", "block/slag_block"));
        slabBlockUncheckedWithItem(BlockRegistry.SLAG_BRICKS_SLAB.get(), blockTexture(ThermalCore.BLOCKS.get(ID_SLAG_BRICKS)), new ResourceLocation("thermal", "block/slag_bricks"));
        stairsBlockUncheckedWithItem(BlockRegistry.SLAG_BRICKS_STAIRS.get(), new ResourceLocation("thermal", "block/slag_bricks"));
        wallBlockUncheckedWithItem(BlockRegistry.SLAG_BRICKS_WALL.get(), new ResourceLocation("thermal", "block/slag_bricks"));
        slabBlockUncheckedWithItem(BlockRegistry.RICH_SLAG_BLOCK_SLAB.get(), blockTexture(ThermalCore.BLOCKS.get(ID_RICH_SLAG_BLOCK)), new ResourceLocation("thermal", "block/rich_slag_block"));
        stairsBlockUncheckedWithItem(BlockRegistry.RICH_SLAG_BLOCK_STAIRS.get(), new ResourceLocation("thermal", "block/rich_slag_block"));
        wallBlockUncheckedWithItem(BlockRegistry.RICH_SLAG_BLOCK_WALL.get(), new ResourceLocation("thermal", "block/rich_slag_block"));
        slabBlockUncheckedWithItem(BlockRegistry.RICH_SLAG_BRICKS_SLAB.get(), blockTexture(ThermalCore.BLOCKS.get(ID_RICH_SLAG_BRICKS)), new ResourceLocation("thermal", "block/rich_slag_bricks"));
        stairsBlockUncheckedWithItem(BlockRegistry.RICH_SLAG_BRICKS_STAIRS.get(), new ResourceLocation("thermal", "block/rich_slag_bricks"));
        wallBlockUncheckedWithItem(BlockRegistry.RICH_SLAG_BRICKS_WALL.get(), new ResourceLocation("thermal", "block/rich_slag_bricks"));

    }


    // --- DEPENDENCIES BLOCKS (resources from other mods)

    /**
     * Identical to {@link BlockStateProvider#slabBlock(SlabBlock, ResourceLocation, ResourceLocation)}
     * however does not check if the texture or model exists. Use for modded textures/models
     */
    public void slabBlockUncheckedWithItem(SlabBlock slab, ResourceLocation doubleslab, ResourceLocation texture) {
        existingFileHelper.trackGenerated(texture, TEXTURE);
        existingFileHelper.trackGenerated(doubleslab, MODEL);

        slabBlock(slab, doubleslab, texture);
        simpleBlockItem(slab, models().getExistingFile(blockTexture(slab)));
    }

    public void stairsBlockUncheckedWithItem(StairBlock stair, ResourceLocation texture) {
        existingFileHelper.trackGenerated(texture, TEXTURE);

        stairsBlock(stair, texture);
        simpleBlockItem(stair, models().getExistingFile(blockTexture(stair)));
    }

    // IMPORTANT: walls also need proper tags to connect and item model
    public void wallBlockUncheckedWithItem(WallBlock wall, ResourceLocation texture) {
        existingFileHelper.trackGenerated(texture, TEXTURE);

        wallBlock(wall ,texture);
    }
}
