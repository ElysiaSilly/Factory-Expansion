package dev.teamcitrusmods.factory_expansion.registry;

import cofh.thermal.core.ThermalCore;
import dev.teamcitrusmods.factory_expansion.FactoryExpansion;
import dev.teamcitrusmods.factory_expansion.block.ColumnBlock;
import dev.teamcitrusmods.factory_expansion.block.FluxLampBlock;
import dev.teamcitrusmods.factory_expansion.block.GirderBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntFunction;

import static cofh.thermal.lib.util.ThermalIDs.ID_SLAG_BLOCK;
import static cofh.thermal.lib.util.ThermalIDs.ID_SLAG_BRICKS;

public class BlockRegistry {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FactoryExpansion.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FactoryExpansion.MODID);


    // Thermal Blocks
    public static final RegistryObject<SlabBlock> SLAG_BLOCK_SLAB = register("slag_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BLOCK))), "thermal");
    public static final RegistryObject<StairBlock> SLAG_BLOCK_STAIRS = register("slag_block_stairs", () -> new StairBlock(() -> ThermalCore.BLOCKS.get(ID_SLAG_BLOCK).defaultBlockState(), BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BLOCK))), "thermal");
    public static final RegistryObject<WallBlock> SLAG_BLOCK_WALL = register("slag_block_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BLOCK))), "thermal");

    public static final RegistryObject<SlabBlock> SLAG_BRICKS_SLAB = register("slag_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BRICKS))), "thermal");
    public static final RegistryObject<StairBlock> SLAG_BRICKS_STAIRS = register("slag_bricks_stairs", () -> new StairBlock(() -> ThermalCore.BLOCKS.get(ID_SLAG_BRICKS).defaultBlockState(), BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BRICKS))), "thermal");
    public static final RegistryObject<WallBlock> SLAG_BRICKS_WALL = register("slag_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BRICKS))), "thermal");

    public static final RegistryObject<SlabBlock> RICH_SLAG_BLOCK_SLAB = register("rich_slag_block_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BLOCK))), "thermal");
    public static final RegistryObject<StairBlock> RICH_SLAG_BLOCK_STAIRS = register("rich_slag_block_stairs", () -> new StairBlock(() -> ThermalCore.BLOCKS.get(ID_SLAG_BLOCK).defaultBlockState(), BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BLOCK))), "thermal");
    public static final RegistryObject<WallBlock> RICH_SLAG_BLOCK_WALL = register("rich_slag_block_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BLOCK))), "thermal");

    public static final RegistryObject<SlabBlock> RICH_SLAG_BRICKS_SLAB = register("rich_slag_bricks_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BRICKS))), "thermal");
    public static final RegistryObject<StairBlock> RICH_SLAG_BRICKS_STAIRS = register("rich_slag_bricks_stairs", () -> new StairBlock(() -> ThermalCore.BLOCKS.get(ID_SLAG_BRICKS).defaultBlockState(), BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BRICKS))), "thermal");
    public static final RegistryObject<WallBlock> RICH_SLAG_BRICKS_WALL = register("rich_slag_bricks_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(ThermalCore.BLOCKS.get(ID_SLAG_BRICKS))), "thermal");


    // Fully Custom Blocks

    public static final RegistryObject<ColumnBlock> DEFAULT_COLUMN = register("default_column", () -> new ColumnBlock(BlockBehaviour.Properties.copy(Blocks.BASALT)));
    public static final RegistryObject<GirderBlock> TEST_GIRDER = register("test_girder", () -> new GirderBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final RegistryObject<FluxLampBlock> FLUX_LAMP = register("flux_lamp",
            () -> new FluxLampBlock(BlockBehaviour.Properties
                    .copy(Blocks.IRON_BLOCK)
                    .lightLevel(litBlockEmission(15))
                    .noOcclusion()));

    public static final RegistryObject<FluxLampBlock> FLUX_LAMP_INVERTED = register("flux_lamp_inverted",
            () -> new FluxLampBlock(BlockBehaviour.Properties
                    .copy(Blocks.IRON_BLOCK)
                    .lightLevel(litBlockEmission(0))
                    .noOcclusion()));

    // Helper Methods

    private static ToIntFunction<BlockState> litBlockEmission(int pLightValue) {
        return (p_50763_) -> {
            return p_50763_.getValue(BlockStateProperties.LIT) ? pLightValue : 15 - pLightValue;
        };
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
            RegistryObject<T> returnBlock = BLOCKS.register(name, block);
            ITEMS.register(name, () -> new BlockItem(returnBlock.get(), new Item.Properties()));
            return returnBlock;
    }

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block, String requirement) {
        if(ModList.get().isLoaded(requirement)) {
            return register(name, block);
        }
        return null;
    }
}
