package com.teamcitrus.factory_expansion.common.flamethrower;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public abstract class Canister {

    // more to come

    public Canister() {}

    // runs on the flamethrower's tick
    public abstract void tick(ItemStack item, Level level, Player player);

}
