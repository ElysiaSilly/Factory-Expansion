package com.teamcitrus.factory_expansion.client.render.misc;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IPreviewBlock;
import com.teamcitrus.factory_expansion.core.Config;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.keys.FEBlockTags;
import com.teamcitrus.factory_expansion.core.util.MathUtils;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import org.joml.Vector3f;
import org.joml.Vector3i;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)

public class BlockPreviewRenderer {

    @SubscribeEvent
    public static void onRenderLevelStageEvent(RenderLevelStageEvent event) {

        if(!Config.BLOCK_PLACEMENT_PREVIEW.get()) return;

        if(!(event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS)) return;
        if(!(Minecraft.getInstance().hitResult instanceof BlockHitResult hitResult)) return;
        if(!(Minecraft.getInstance().hitResult.getType() == HitResult.Type.BLOCK)) return;

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if(player == null) return;

        ItemStack item = player.getMainHandItem();

        if(item.isEmpty()) player.getOffhandItem(); // todo : this isnt working for some reason

        if(item.getItem() instanceof BlockItem blockItem) {
            if(blockItem.getBlock().defaultBlockState().is(FEBlockTags.HAS_PLACEMENT_PREVIEW)) {

                PoseStack stack = event.getPoseStack();

                BlockPlaceContext context = new BlockPlaceContext(player, player.getUsedItemHand(), player.getMainHandItem(), hitResult);

                BlockPos pos = context.getClickedPos();

                Camera cam = minecraft.gameRenderer.getMainCamera();
                stack.translate(pos.getX() - cam.getPosition().x, pos.getY() - cam.getPosition().y, pos.getZ() - cam.getPosition().z);
                stack.pushPose();

                if(blockItem.getBlock() instanceof IPreviewBlock block) {
                    block.renderPreview(hitResult, context, blockItem.getBlock(), minecraft, stack);
                } else {
                    renderGhostBlock(blockItem.getBlock(), context, stack);
                }
            }
        }
    }

    public static void renderGhostBlock(Block block, BlockPlaceContext context, PoseStack stack) {

        BlockState placement = block.getStateForPlacement(context);
        if(placement == null) return;

        if(Minecraft.getInstance().level instanceof BlockAndTintGetter tint) {

            float dist = MathUtils.distance(context.getPlayer().position(), context.getClickedPos().getCenter());

            float falloff = 0;

            if(dist < 2.5) falloff = (float) (dist - 2.5);

            RenderSystem.enableBlend();
            RenderSystem.blendFunc(GlStateManager.SourceFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.4F + falloff);

            Minecraft.getInstance().getBlockRenderer().renderBatched(
                    placement,
                    context.getClickedPos(),
                    tint,
                    stack,
                    Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(RenderType.translucent()),
                    true,
                    Minecraft.getInstance().level.getRandom()
            );

            RenderSystem.disableBlend();
        }
    }
}