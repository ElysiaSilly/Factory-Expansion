package com.teamcitrus.factory_expansion.common.canister;

import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class NitroCanisterType extends CanisterType {

    public NitroCanisterType() {}

    @Override
    public void process(ItemStack flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector) {

        if(level.isClientSide) return;

        level.explode(null, player.position().x, player.position().y + 1, player.position().z, 10, Level.ExplosionInteraction.TNT);
    }
}
