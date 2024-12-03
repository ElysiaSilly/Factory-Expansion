package com.teamcitrus.factory_expansion.client.render.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IPreviewBlock;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
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

        Minecraft minecraft = Minecraft.getInstance();
        Player player = minecraft.player;

        if(player == null) return;

        if(!(player.getMainHandItem().getItem() instanceof BlockItem item)) return;
        if(!(item.getBlock() instanceof IPreviewBlock block)) return;
        if(!(Minecraft.getInstance().hitResult instanceof BlockHitResult hitResult)) return;
        if(!(Minecraft.getInstance().hitResult.getType() == HitResult.Type.BLOCK)) return;
        if(!(event.getStage() == RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS)) return;

        PoseStack stack = event.getPoseStack();

        BlockPlaceContext context = new BlockPlaceContext(player, player.getUsedItemHand(), player.getMainHandItem(), hitResult);

        BlockPos pos = context.getClickedPos();

        Camera cam = minecraft.gameRenderer.getMainCamera();
        stack.translate(pos.getX() - cam.getPosition().x, pos.getY() - cam.getPosition().y, pos.getZ() - cam.getPosition().z);
        stack.pushPose();

        block.renderPreview(hitResult, context, item.getBlock(), minecraft, stack);
    }
}
