package com.teamcitrus.factory_expansion.client.render.be;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.teamcitrus.factory_expansion.common.block.DisplayBlock;
import com.teamcitrus.factory_expansion.common.block.be.DisplayBlockBE;
import com.teamcitrus.factory_expansion.core.Config;
import com.teamcitrus.factory_expansion.core.util.ColourUtils;
import com.teamcitrus.factory_expansion.core.util.RGBA;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class DisplayBlockRenderer implements BlockEntityRenderer<DisplayBlockBE> {

    public DisplayBlockRenderer(BlockEntityRendererProvider.Context context) {}

    private static final float magicNumber = -0.08f;

    @Override
    public void render(DisplayBlockBE be, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

        poseStack.translate(0.5, 0.55, 0.5);
        poseStack.mulPose(Axis.YN.rotationDegrees(be.getBlockState().getValue(DisplayBlock.FACING).getOpposite().toYRot()));
        Matrix4f matrix4f = poseStack.last().pose();
        matrix4f.scale(magicNumber, magicNumber, magicNumber);
        matrix4f.translate(0, -3f, -4.5f);

        int colour = Config.DISPLAY_COLOUR_VARIATION.get() ? be.getSeededColour() : be.getColour();

        if(Config.DISPLAY_FLICKERING.get()) colour = flickering(colour, be);

        drawText(be, colour, matrix4f, bufferSource, true);

        if(Config.DISPLAY_CHROMATIC_ABERRATION.get()) {
            matrix4f.translate(-0.3f, -0.3f, -0.05f);

            RGBA c = ColourUtils.DecToRGBA(colour);

            colour = ColourUtils.RGBAToDec(new RGBA(c.red() / 2, (int) (c.green() / 1.5), c.blue() / 4, c.alpha())); // todo : proper chromatic aberration

            drawText(be, colour, matrix4f, bufferSource, false);
        }
    }

    private int flickering(int c, DisplayBlockBE be) {
        RGBA colour = ColourUtils.DecToRGBA(c);

        int ran = be.getLevel().random.nextIntBetweenInclusive(-5000, 100);
        ran = ran >= 98 ? ran : 1;

        colour.shade((float) (ran));

        return ColourUtils.RGBAToDec(colour);
    }

    private void drawText(DisplayBlockBE be, int colour, Matrix4f matrix4f, MultiBufferSource bufferSource, boolean dropShadow) {
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
}
