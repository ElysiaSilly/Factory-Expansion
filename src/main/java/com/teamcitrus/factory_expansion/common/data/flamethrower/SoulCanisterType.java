package com.teamcitrus.factory_expansion.common.data.flamethrower;

import com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData.CanisterType;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
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


        Vec3 hitResult = minecraft.hitResult.getLocation();//.subtract(0, 1.5, 0);



        Vec3 particleOrigin = player.getRopeHoldPosition(Minecraft.getInstance().getFps());

        Vec3 origin = particleOrigin.subtract(playerLookVector);//.add(normal.x, 0,normal.z);

        Vec3 ray = hitResult.subtract(origin).normalize();



        level.addParticle(ParticleTypes.SOUL_FIRE_FLAME, origin.x, origin.y, origin.z, ray.x, ray.y, ray.z);
        level.addParticle(ParticleTypes.LARGE_SMOKE, origin.x, origin.y, origin.z, ray.x, ray.y, ray.z);
    }

    /*
    public Vec3 getRopeHoldPosition(float partialTicks, Player player) {
        double d0 = 0.22 * (player.getMainArm() == HumanoidArm.RIGHT ? -1.0 : 1.0);
        float f = Mth.lerp(partialTicks * 0.5F, player.getXRot(), player.xRotO) * (float) (Math.PI / 180.0);
        float f1 = Mth.lerp(partialTicks, player.yBodyRotO, player.yBodyRot) * (float) (Math.PI / 180.0);
        if (player.isFallFlying() || player.isAutoSpinAttack()) {
            Vec3 vec31 = player.getViewVector(partialTicks);
            Vec3 vec3 = player.getDeltaMovement();
            double d6 = vec3.horizontalDistanceSqr();
            double d3 = vec31.horizontalDistanceSqr();
            float f2;
            if (d6 > 0.0 && d3 > 0.0) {
                double d4 = (vec3.x * vec31.x + vec3.z * vec31.z) / Math.sqrt(d6 * d3);
                double d5 = vec3.x * vec31.z - vec3.z * vec31.x;
                f2 = (float)(Math.signum(d5) * Math.acos(d4));
            } else {
                f2 = 0.0F;
            }

            return player.getPosition(partialTicks).add(new Vec3(d0, -0.11, 0.85).zRot(-f2).xRot(-f).yRot(-f1));
        } else if (player.isVisuallySwimming()) {
            return player.getPosition(partialTicks).add(new Vec3(d0, 0.2, -0.15).xRot(-f).yRot(-f1));
        } else {
            double d1 = player.getBoundingBox().getYsize() - 1.0;
            double d2 = player.isCrouching() ? -0.2 : 0.07;
            return player.getPosition(partialTicks).add(new Vec3(d0, d1, d2).yRot(-f1));
        }
    }

     */
}
