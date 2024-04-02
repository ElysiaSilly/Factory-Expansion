package dev.teamcitrusmods.thermal_fne.datagen;

import dev.teamcitrusmods.thermal_fne.ThermalFNE;
import dev.teamcitrusmods.thermal_fne.datagen.provider.FNEBlockStateProvider;
import dev.teamcitrusmods.thermal_fne.datagen.provider.FNEItemModelProvider;
import dev.teamcitrusmods.thermal_fne.datagen.provider.FNETagProvider;
import dev.teamcitrusmods.thermal_fne.datagen.provider.lang.EnUsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = ThermalFNE.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class FNEDatagen {
    @SubscribeEvent
    public static void generate(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper file = event.getExistingFileHelper();

        generator.addProvider(event.includeClient(), new FNEBlockStateProvider(packOutput, file));
        generator.addProvider(event.includeClient(), new EnUsProvider(packOutput, "en_us"));
        generator.addProvider(event.includeServer(), new FNEItemModelProvider(packOutput, file));

        CompletableFuture<HolderLookup.Provider> complete = event.getLookupProvider();
        FNETagProvider.FNEBlockTags blockTags = new FNETagProvider.FNEBlockTags(packOutput, complete, file);
        generator.addProvider(event.includeServer(), blockTags);
        // generator.addProvider(event.includeServer(), new TutItemTags(packOutput, lookupProvider, blockTags, event.getExistingFileHelper()));
        // generator.addProvider(event.includeServer(), new TutRecipes(packOutput));
        // generator.addProvider(event.includeServer(), new LootTableProvider(packOutput, Collections.emptySet(),
        //         List.of(new LootTableProvider.SubProviderEntry(TutLootTables::new, LootContextParamSets.BLOCK))));
    }
}
