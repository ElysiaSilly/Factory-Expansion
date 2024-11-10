package com.teamcitrus.factory_expansion.common.data.flamethrower;

import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterType;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
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
    public void process(FlamethrowerItem flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector) {

        // todo : absolutely cooked rn figure this out later

        if(minecraft.hitResult == null) return;
        Vec3 hitResult = minecraft.hitResult.getLocation().subtract(0, 1.5, 0);

        Vec3 normal = playerLookVector.normalize();

        Vec3 origin = player.position().subtract(playerLookVector).add(normal.x, -1,normal.z * 2);

        Vec3 ray = hitResult.subtract(origin).normalize();

        level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, origin.x, origin.y + 1.4, origin.z, ray.x, ray.y, ray.z);
        level.addParticle(ParticleTypes.LARGE_SMOKE, origin.x, origin.y + 1.4, origin.z, ray.x, ray.y, ray.z);
    }
}
