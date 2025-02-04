package com.teamcitrus.factory_expansion.common.item;

import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PoppedDrywallFoodItem extends Item {

    public PoppedDrywallFoodItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {

        if(level.isClientSide) return stack;

        if(!(livingEntity instanceof Player player)) return stack;

        FoodProperties foodproperties = stack.getFoodProperties(livingEntity);

        stack.hurtAndBreak(1, player, null);
        return stack; //foodproperties != null ? livingEntity.eat(level, stack.copy(), foodproperties) : stack;
        //return stack;
    }

    @Override
    public boolean isDamageable(ItemStack stack) {
        return super.isDamageable(stack);
    }

    @Override
    public String getDescriptionId() {
        return Component.translatable(Util.makeDescriptionId("item", BuiltInRegistries.ITEM.getKey(this)) + "." + FactoExpa.drywall).getString();
    }
}
