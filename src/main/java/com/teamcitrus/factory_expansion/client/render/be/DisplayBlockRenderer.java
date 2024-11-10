package com.teamcitrus.factory_expansion.client.render.be;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamcitrus.factory_expansion.common.block.DisplayBlock;
import com.teamcitrus.factory_expansion.common.block.be.DisplayBlockBE;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import org.joml.Matrix4f;

public class DisplayBlockRenderer implements BlockEntityRenderer<DisplayBlockBE> {

    public DisplayBlockRenderer(BlockEntityRendererProvider.Context context) {}

    @Override
    public void render(DisplayBlockBE be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {


        poseStack.translate(0.46, 0.77, 0.87);

        Matrix4f matrix4f = poseStack.last().pose();

        float scale = 0.08f;

        matrix4f.scale(scale, scale, scale);
        matrix4f.rotate(Axis.ZP.rotationDegrees(180));
        matrix4f.rotate(Axis.YP.rotationDegrees(be.getBlockState().getValue(DisplayBlock.FACING).toYRot() - 180));

        //matrix4f.translate(-5.5f, -9.9f, 10.6f); 15728880

        String text = String.valueOf(be.getCharacter());

        float f2 = (float)(-Minecraft.getInstance().font.width(text) / 2);

        Minecraft.getInstance().font.drawInBatch(text, f2, 0, be.getColour(), false, matrix4f, bufferSource, Font.DisplayMode.POLYGON_OFFSET, 0, 15728880);

    }
}
