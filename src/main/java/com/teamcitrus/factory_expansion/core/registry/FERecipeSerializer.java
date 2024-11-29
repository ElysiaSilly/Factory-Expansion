package com.teamcitrus.factory_expansion.core.registry;

import com.teamcitrus.factory_expansion.common.recipe.FTAlloying.FTAlloyingRecipe;
import com.teamcitrus.factory_expansion.common.recipe.FTAlloying.FTAlloyingRecipeSerializer;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FERecipeSerializer {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, FactoExpa.MODID);

    public static final Supplier<RecipeSerializer<FTAlloyingRecipe>> FLAMETHROWER_ALLOYING =
            RECIPE_SERIALIZERS.register("flamethrower_alloying", FTAlloyingRecipeSerializer::new);
}
