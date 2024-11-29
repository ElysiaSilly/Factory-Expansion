package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.recipe.FTAlloying.FTAlloyingRecipe;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FERecipeTypes {
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, FactoExpa.MODID);

    public static final Supplier<RecipeType<FTAlloyingRecipe>> FLAMETHROWER_ALLOYING =
            RECIPE_TYPES.register(
                    "flamethrower_alloying",
                    // We need the qualifying generic here due to generics being generics.
                    () -> RecipeType.<FTAlloyingRecipe>simple(ResourceLocation.fromNamespaceAndPath(FactoExpa.MODID, "flamethrower_alloying"))
            );
}
