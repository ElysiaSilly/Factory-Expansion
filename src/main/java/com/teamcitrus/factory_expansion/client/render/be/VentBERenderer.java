package com.teamcitrus.factory_expansion.client.render.be;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.teamcitrus.factory_expansion.common.block.be.VentBE;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.joml.Matrix4f;

public class VentBERenderer implements BlockEntityRenderer<VentBE> {

    public VentBERenderer(BlockEntityRendererProvider.Context context) {}


    // todo
    @Override
    public void render(VentBE blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        VertexConsumer con = bufferSource.getBuffer(RenderType.cutout());
        TextureAtlasSprite sprite = Minecraft.getInstance().getTextureAtlas(TextureAtlas.LOCATION_BLOCKS).apply(FactoExpa.location("block/industrial_fan/large_blade_static"));

        float help = 1;

        float minU = sprite.getU(help * 1);
        float maxU = sprite.getU((1 - help) * 1);
        float minV = sprite.getV(help * 1);
        float maxV = sprite.getV((1 - help) * 1);

        Matrix4f matrix4f = poseStack.last().pose();

        float angleX = 0.1f * (Minecraft.getInstance().player.tickCount + partialTick) * 360;

        poseStack.translate(.5, .5, .5);

        poseStack.mulPose(Axis.YN.rotationDegrees(angleX));

        float s = -1.5f;
        float e = 1.5f;

        con.addVertex(matrix4f, s, 0, s).setColor(-1, -1, -1, -1).setUv(minU, minV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, s, 0, e).setColor(-1, -1, -1, -1).setUv(minU, maxV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, e, 0, e).setColor(-1, -1, -1, -1).setUv(maxU, maxV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, e, 0, s).setColor(-1, -1, -1, -1).setUv(maxU, minV).setLight(packedLight).setNormal(0, 1, 0);

        poseStack.mulPose(Axis.YN.rotationDegrees(30));


        con.addVertex(matrix4f, s, 0, s).setColor(-1, -1, -1, -1).setUv(minU, minV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, s, 0, e).setColor(-1, -1, -1, -1).setUv(minU, maxV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, e, 0, e).setColor(-1, -1, -1, -1).setUv(maxU, maxV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, e, 0, s).setColor(-1, -1, -1, -1).setUv(maxU, minV).setLight(packedLight).setNormal(0, 1, 0);

        poseStack.mulPose(Axis.YN.rotationDegrees(30));


        con.addVertex(matrix4f, s, 0, s).setColor(-1, -1, -1, -1).setUv(minU, minV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, s, 0, e).setColor(-1, -1, -1, -1).setUv(minU, maxV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, e, 0, e).setColor(-1, -1, -1, -1).setUv(maxU, maxV).setLight(packedLight).setNormal(0, 1, 0);
        con.addVertex(matrix4f, e, 0, s).setColor(-1, -1, -1, -1).setUv(maxU, minV).setLight(packedLight).setNormal(0, 1, 0);
    }
}
