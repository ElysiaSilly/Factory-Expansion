package com.teamcitrus.factory_expansion.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class FlamethrowerRenderer extends BlockEntityWithoutLevelRenderer {

    public FlamethrowerRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource buffer, int packedLight, int packedOverlay) {

        if(!(stack.getItem() instanceof FlamethrowerItem flamethrower)) return;

        BakedModel model = Minecraft.getInstance().getModelManager().getModel(ModelResourceLocation.standalone(ResourceLocation.parse(
                "minecraft:models/block/diamond_block.json"
        )));

        //System.out.println(
        //Minecraft.getInstance().getModelManager().getMissingModel()
        //        model.getOverrides().getOverrides()
        //);

        VertexConsumer consumer = buffer.getBuffer(RenderType.cutout());

        Minecraft.getInstance().getBlockRenderer().getModelRenderer().renderModel(poseStack.last(), consumer, null, model, -1, -1, -1, packedLight, packedOverlay);

        //System.out.println(model);

        //Minecraft.getInstance().getBlockRenderer().renderSingleBlock(Blocks.ACACIA_PLANKS.defaultBlockState(), poseStack, buffer, packedLight, packedOverlay);

    }
}
