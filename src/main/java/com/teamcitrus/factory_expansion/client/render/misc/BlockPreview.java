package com.teamcitrus.factory_expansion.client.render.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IPreviewBlock;
import com.teamcitrus.factory_expansion.core.Config;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import com.teamcitrus.factory_expansion.core.keys.FEBlockTags;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = FactoExpa.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)

public class BlockPreview {

    @SubscribeEvent
    public static void onRenderLevelEvent(RenderLevelStageEvent event) {

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
                    MiscRendering.renderGhostBlock(blockItem.getBlock(), context, stack);
                }
            }
        }
    }
}
