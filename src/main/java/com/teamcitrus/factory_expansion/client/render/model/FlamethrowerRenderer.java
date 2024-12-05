package com.teamcitrus.factory_expansion.client.render.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class FlamethrowerRenderer extends BlockEntityWithoutLevelRenderer {

    public FlamethrowerRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        if(!(stack.getItem() instanceof FlamethrowerItem flamethrower)) return;

        Player player = Minecraft.getInstance().player;

        if(Minecraft.getInstance().hitResult == null) return;
        Vec3 hitResult = Minecraft.getInstance().hitResult.getLocation().subtract(0, 1.5, 0);

        Vec3 normal = player.getLookAngle().normalize();

        Vec3 origin = player.position().subtract(player.getLookAngle()).add(normal.x, -1,normal.z * 2);

        Vec3 ray = hitResult.subtract(origin).normalize();

        //poseStack.mulPose(Axis.YP.rotationDegrees((float) ray.y));
        //poseStack.mulPose(Axis.ZP.rotationDegrees((float) ray.z));
        //poseStack.mulPose(Axis.XP.rotationDegrees((float) ray.x));

        VertexConsumer consumer = buffer.getBuffer(RenderType.entityCutout(ResourceLocation.withDefaultNamespace("textures/block/diamond_block.png")));


        consumer.setNormal(0, 1, 0);

        BakedModel model = Minecraft.getInstance().getModelManager().getModel(ModelResourceLocation.standalone(FactoExpa.location("special/flamethrower")));

        //BakedModel model = Minecraft.getInstance().getModelManager().getModel(ModelResourceLocation.vanilla("spyglass", "inventory")); //ResourceLocation.withDefaultNamespace("block/lectern")));

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), consumer, null, model, 255, 255, 255, packedLight, packedOverlay);
    }
}
