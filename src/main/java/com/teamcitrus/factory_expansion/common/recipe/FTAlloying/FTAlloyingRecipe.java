package com.teamcitrus.factory_expansion.common.recipe.FTAlloying;

import com.teamcitrus.factory_expansion.core.registry.FERecipeSerializer;
import com.teamcitrus.factory_expansion.core.registry.FERecipeTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.util.RecipeMatcher;
import net.neoforged.neoforge.items.wrapper.RecipeWrapper;

public class FTAlloyingRecipe implements Recipe<RecipeWrapper> {

    private static final int MaxInputs = 4;

    private final NonNullList<Ingredient> ingredients;
    private final ItemStack result;

    // blaze fuel canister or soul blaze fuel canister
    // private static FireType fireType;

    private final float experience;
    private final int time;

    public FTAlloyingRecipe(NonNullList<Ingredient> ingredients, ItemStack result, float experience, int time) {
        this.ingredients = ingredients;
        this.result = result;
        this.experience = experience;
        this.time = time;
    }


    public ItemStack getResult() {
        return result;
    }

    public float getExperience() {
        return experience;
    }

    public int getTime() {
        return time;
    }

    public void getFlame() {
        // todo
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return ingredients;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return result;
    }

    @Override
    public ItemStack assemble(RecipeWrapper wrapper, HolderLookup.Provider provider) {
        return result.copy();
    }

    @Override
    public boolean matches(RecipeWrapper wrapper, Level level) {
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();

        for (int j = 0; j < wrapper.size(); ++j) {

            ItemStack itemstack = wrapper.getItem(j);
            inputs.add(itemstack);
        }

        return RecipeMatcher.findMatches(inputs, ingredients) != null;
    }

    @Override
    public boolean canCraftInDimensions(int height, int width) {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return FERecipeSerializer.FLAMETHROWER_ALLOYING.get();
    }

    @Override
    public RecipeType<?> getType() {
        return FERecipeTypes.FLAMETHROWER_ALLOYING.get();
    }
}
