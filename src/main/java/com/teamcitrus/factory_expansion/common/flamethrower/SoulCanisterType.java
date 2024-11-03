package com.teamcitrus.factory_expansion.common.flamethrower;

import com.teamcitrus.factory_expansion.common.flamethrower.canisterData.CanisterType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SoulCanisterType extends CanisterType {

    public SoulCanisterType(Component name, ResourceLocation icon) {
        super(name, icon);
    }

    @Override
    public void process(ItemStack flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector) {

        level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, player.position().x, player.position().y + 1.4, player.position().z, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z);
        level.addParticle(ParticleTypes.LARGE_SMOKE, player.position().x, player.position().y + 1.4, player.position().z, player.getLookAngle().x, player.getLookAngle().y, player.getLookAngle().z);
    }
}
