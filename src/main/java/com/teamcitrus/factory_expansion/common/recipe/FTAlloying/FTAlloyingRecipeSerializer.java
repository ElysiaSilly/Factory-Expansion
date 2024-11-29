package com.teamcitrus.factory_expansion.common.recipe.FTAlloying;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;

public class FTAlloyingRecipeSerializer implements RecipeSerializer<FTAlloyingRecipe> {

    public static final MapCodec<FTAlloyingRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(

            Ingredient.LIST_CODEC_NONEMPTY.fieldOf("ingredients").xmap(ingredients -> {
                NonNullList<Ingredient> nonNullList = NonNullList.create();
                nonNullList.addAll(ingredients);
                return nonNullList;
            }, ingredients -> ingredients).forGetter(FTAlloyingRecipe::getIngredients),
            ItemStack.CODEC.fieldOf("result").forGetter(FTAlloyingRecipe::getResult),
            Codec.FLOAT.optionalFieldOf("experience", 0.0F).forGetter(FTAlloyingRecipe::getExperience),
            Codec.INT.optionalFieldOf("time", 200).forGetter(FTAlloyingRecipe::getTime)
    ).apply(inst, FTAlloyingRecipe::new));

    /*
    public static final StreamCodec<RegistryFriendlyByteBuf, FTAlloyingRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    //ByteBufCodecs.idMapper(Item.BLOCK_STATE_REGISTRY), RightClickBlockRecipe::getInputState,

                    //NonNullList<Ingredient>.CO

                    //Ingredient.CONTENTS_STREAM_CODEC, FTAlloyingRecipe::getIngredients,
                    //ItemStack.STREAM_CODEC, FTAlloyingRecipe::getResult,
                    //ByteBufCodecs.INT, FTAlloyingRecipe::getTime,
                    //ByteBufCodecs.FLOAT, FTAlloyingRecipe::getExperience,


                    FTAlloyingRecipe::new
            );

     */

    @Override
    public MapCodec<FTAlloyingRecipe> codec() {
        return CODEC;
    }

    @Override
    public StreamCodec<RegistryFriendlyByteBuf, FTAlloyingRecipe> streamCodec() {
        return null;
    }
}
