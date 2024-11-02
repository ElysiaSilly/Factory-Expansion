package com.teamcitrus.factory_expansion.common.flamethrower;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class CanisterType {

    final int entityBurnTime;
    final int burnDamage;

    // more to come
    public CanisterType(int entityBurnTime, int burnDamage) {
        this.entityBurnTime = entityBurnTime;
        this.burnDamage = burnDamage;
    }

    // runs on the flamethrower's tick
    public abstract void process(ItemStack flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector);
    // put whatever you wanna do when this canister type is being used in the flamethrower here

    //public CanisterType e() {
    //    return this;
    //}
}
