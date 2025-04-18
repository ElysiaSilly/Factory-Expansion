package com.elysiasilly.fne.client.render.be;

import com.elysiasilly.babel.util.conversions.ColourConversions;
import com.elysiasilly.babel.util.resource.RGBA;
import com.elysiasilly.fne.common.be.DisplayBE;
import com.elysiasilly.fne.common.block.DisplayBlock;
import com.elysiasilly.fne.core.FEConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

// TODO clean this up wtf

public class DisplayBERenderer implements BlockEntityRenderer<DisplayBE> {

    public DisplayBERenderer(BlockEntityRendererProvider.Context context) {}

    private static final float magicNumber = -0.08f;

    @Override
    public void render(DisplayBE be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        if(!be.getBlockState().getValue(BlockStateProperties.POWERED)) return;
        if(be.getString().isEmpty()) return;

        poseStack.translate(0.5, 0.55, 0.5);
        poseStack.mulPose(Axis.YN.rotationDegrees(be.getBlockState().getValue(DisplayBlock.FACING).getOpposite().toYRot()));
        Matrix4f matrix4f = poseStack.last().pose();
        matrix4f.scale(magicNumber, magicNumber, magicNumber);
        matrix4f.translate(0, -3f, -4.5f);

        int colour = FEConfig.DISPLAY_COLOUR_VARIATION.get() ? be.getSeededColour() : be.getColour();

        if(FEConfig.DISPLAY_FLICKERING.get()) colour = flickering(colour, be);

        drawText(be, colour, matrix4f, bufferSource, false);

        if(FEConfig.DISPLAY_CHROMATIC_ABERRATION.get()) {
            matrix4f.translate(-0.3f, -0.3f, -0.05f);

            RGBA c = ColourConversions.rgbaD(colour);

            colour = ColourConversions.dec(new RGBA(c.r() / 2, (int) (c.g() / 1.5), c.b() / 4, c.a())); // todo : proper chromatic aberration

            drawText(be, colour, matrix4f, bufferSource, false);
        }
    }

    private int flickering(int c, DisplayBE be) {
        RGBA colour = ColourConversions.rgbaD(c);

        int ran = be.getLevel().random.nextIntBetweenInclusive(-5000, 100);
        ran = ran >= 98 ? ran : 1;

        colour.shade((float) (ran));

        return ColourConversions.dec(colour);
    }

    private void drawText(DisplayBE be, int colour, Matrix4f matrix4f, MultiBufferSource bufferSource, boolean dropShadow) {
        Minecraft.getInstance().font.drawInBatch(
                Component.literal(be.getString()).withStyle(Style.EMPTY.withFont(ResourceLocation.fromNamespaceAndPath("minecraft", be.getFont()))),
                (float) -Minecraft.getInstance().font.width(be.getString()) / 2,
                0,
                colour,
                dropShadow,
                matrix4f,
                bufferSource,
                Font.DisplayMode.NORMAL,
                0,
                15728880
        );
    }

    @Override
    public boolean shouldRender(DisplayBE blockEntity, Vec3 cameraPos) {
        return FEConfig.DISPLAY_INFINITE_RENDER.get() || BlockEntityRenderer.super.shouldRender(blockEntity, cameraPos);
    }

    @Override
    public AABB getRenderBoundingBox(DisplayBE blockEntity) {
        return FEConfig.DISPLAY_INFINITE_RENDER.get() ? AABB.INFINITE : BlockEntityRenderer.super.getRenderBoundingBox(blockEntity);
    }
}
