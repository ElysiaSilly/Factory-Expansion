package com.teamcitrus.factory_expansion.client.render.misc;

import com.mojang.blaze3d.vertex.PoseStack;
import com.teamcitrus.factory_expansion.common.block.interfaces.block.IPreviewBlock;
import com.teamcitrus.factory_expansion.core.FactoExpa;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.BlockModelShaper;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.model.data.ModelData;
import org.joml.Matrix4f;

import java.util.Iterator;

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






        //BlockModelShaper modelShaper = new BlockModelShaper(Minecraft.getInstance().getModelManager());
//
        //BakedModel model = modelShaper.getBlockModel(block);
//
        ////System.out.println(MusicaRenderTypes.getTestingShader().outline());
//
        //Iterator<RenderType> renderTypeIterator = model.getRenderTypes(block, RandomSource.create(42L), ModelData.EMPTY).iterator();
//
        //while(renderTypeIterator.hasNext()) {
        //    RenderType rt = renderTypeIterator.next();
        //    if(Minecraft.getInstance().level instanceof BlockAndTintGetter tint) {
//
        //        Minecraft.getInstance().getBlockRenderer().renderBatched(
        //                block,
        //                blockPos,
        //                tint,
        //                event.getPoseStack(),
        //                Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(MusicaRenderTypes.getTestingShader()),
        //                //Minecraft.getInstance().renderBuffers().bufferSource().getBuffer(rt),
        //                true,
        //                Minecraft.getInstance().level.getRandom()
        //        );
        //    }}
//
//
    }
}
