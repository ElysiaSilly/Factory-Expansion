package com.teamcitrus.factory_expansion.common.flamethrower;

import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterType;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ExplosionDamageCalculator;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NitroCanisterType extends CanisterType {

    public NitroCanisterType(Component name, ResourceLocation icon) {
        super(name, icon);
    }

    @Override
    public void process(ItemStack flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector) {

        if(level.isClientSide) return;

        level.explode(null, player.position().x, player.position().y + 1, player.position().z, 10, Level.ExplosionInteraction.TNT);
    }
}
