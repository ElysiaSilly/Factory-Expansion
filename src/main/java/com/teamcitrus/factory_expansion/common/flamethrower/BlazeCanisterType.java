package com.teamcitrus.factory_expansion.common.flamethrower;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class BlazeCanisterType extends CanisterType {

    public BlazeCanisterType() {
        super(1, 1);
    }

    @Override
    public void process(ItemStack flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector) {

        level.addParticle(ParticleTypes.FLAME, player.position().x, player.position().y + 1.4, player.position().z, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z);
        level.addParticle(ParticleTypes.LARGE_SMOKE, player.position().x, player.position().y + 1.4, player.position().z, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z);

    }
}
