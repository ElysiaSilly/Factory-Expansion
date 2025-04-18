package com.elysiasilly.fne.mixin.client;

import com.elysiasilly.fne.common.item.FlamethrowerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HumanoidModel.class)
public abstract class HumanoidModelMixin<T extends LivingEntity> extends AgeableListModel<T> implements ArmedModel, HeadedModel {


    @Shadow @Final public ModelPart rightArm;

    @Shadow @Final public ModelPart leftArm;

    @Shadow public HumanoidModel.ArmPose leftArmPose;

    @Inject(method = "setupAnim(Lnet/minecraft/world/entity/LivingEntity;FFFFF)V", at = @At(
            value = "INVOKE_ASSIGN",
            target = "Lnet/minecraft/util/Mth;cos(F)F",
            ordinal = 1
    ))

    private void modifyArmSwing(T livingEntity, float f, float g, float h, float i, float j, CallbackInfo info) {
        if(!livingEntity.isSprinting() && !livingEntity.isSwimming() && !livingEntity.isFallFlying()) {
            Minecraft mc = Minecraft.getInstance();

            ItemStack rightStack = mc.options.mainHand().get() == HumanoidArm.RIGHT ? livingEntity.getMainHandItem() : livingEntity.getOffhandItem();
            ItemStack leftStack = mc.options.mainHand().get() == HumanoidArm.RIGHT ? livingEntity.getOffhandItem() : livingEntity.getMainHandItem();

            boolean flag = rightStack.getItem() instanceof FlamethrowerItem || leftStack.getItem() instanceof FlamethrowerItem;

            if(flag) {
                this.leftArm.setRotation(0, 0, 0);
                this.rightArm.setRotation(0, 0, 0);
            }
        }
    }

}
