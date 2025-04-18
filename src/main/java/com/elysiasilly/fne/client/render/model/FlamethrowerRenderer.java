package com.elysiasilly.fne.client.render.model;

import com.elysiasilly.babel.api.client.model.ModelLoader;
import com.elysiasilly.babel.api.client.model.Util;
import com.elysiasilly.babel.api.client.model.resources.CubeModelElement;
import com.elysiasilly.babel.api.client.model.resources.Face;
import com.elysiasilly.babel.api.client.model.resources.ModelElement;
import com.elysiasilly.babel.api.client.model.resources.model.Model;
import com.elysiasilly.babel.util.resource.RGBA;
import com.elysiasilly.babel.util.resource.UV;
import com.elysiasilly.babel.util.utils.RenderUtil;
import com.elysiasilly.fne.common.component.FlamethrowerComponent;
import com.elysiasilly.fne.common.item.FlamethrowerItem;
import com.elysiasilly.fne.FactoExpa;
import com.elysiasilly.fne.core.registry.FEComponents;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class FlamethrowerRenderer extends BlockEntityWithoutLevelRenderer {

    public FlamethrowerRenderer() {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext displayContext, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {

        if(!(stack.getItem() instanceof FlamethrowerItem flamethrower)) return;
        Minecraft mc = Minecraft.getInstance();

        Model model = ModelLoader.model(FactoExpa.location("models/special/flamethrower.bbmodel"));

        if(displayContext.equals(ItemDisplayContext.GUI)) {
            poseStack.mulPose(Axis.XP.rotationDegrees(22.5f));
            poseStack.mulPose(Axis.ZP.rotationDegrees(22.5f));
            poseStack.translate(.5, 0,0 );
        } else if(displayContext.equals(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND)) {
            poseStack.translate(-.5, 0,0 );
        } else {
            poseStack.translate(-.3, 0, 0);
            poseStack.mulPose(Axis.XP.rotationDegrees(75));
        }

        for(ModelElement object : model.getOutliner("base").children()) {
            if(object instanceof CubeModelElement cube) {
                poseStack.pushPose();

                Vector3f pos = cube.pivot();

                Vector3f rot = cube.rotation();
                poseStack.rotateAround(new Quaternionf().rotationXYZ(rot.x, rot.y, rot.z), pos.x, pos.y, pos.z);

                Util.render(cube, multiBufferSource.getBuffer(RenderType.CUTOUT), poseStack.last().pose(), packedLight, RGBA.NULL);

                poseStack.popPose();
            }
        }

        ModelElement c = model.getElement("canister_rack");
        
        if(c instanceof CubeModelElement cube) {
            poseStack.pushPose();

            Vector3f pos = cube.pivot();

            Vector3f rot = cube.rotation();
            poseStack.rotateAround(new Quaternionf().rotationXYZ(rot.x, rot.y, rot.z), pos.x, pos.y, pos.z);

            Util.render(cube, multiBufferSource.getBuffer(RenderType.CUTOUT), poseStack.last().pose(), packedLight, RGBA.NULL);

            poseStack.popPose();
        }

        if(stack.has(FEComponents.FLAMETHROWER)) {
            FlamethrowerComponent component = stack.get(FEComponents.FLAMETHROWER);

            for(ModelElement object : model.getOutliner("skin").children()) {
                if(object instanceof CubeModelElement cube) {
                    poseStack.pushPose();

                    Vector3f pos = cube.pivot();

                    Vector3f rot = cube.rotation();
                    poseStack.rotateAround(new Quaternionf().rotationXYZ(rot.x, rot.y, rot.z), pos.x, pos.y, pos.z);

                    renderSpecial(cube, multiBufferSource.getBuffer(RenderType.CUTOUT), poseStack.last().pose(), packedLight, RGBA.NULL, stack.has(FEComponents.SKIN_TOKEN) ? stack.get(FEComponents.SKIN_TOKEN).skin() : FactoExpa.location("default"));

                    poseStack.popPose();
                }
            }

            for(int i = 0; i <= component.size(); i++) {
                ModelElement loc = model.getElement("slot_" + (i + 1));
                if(loc != null) {

                    poseStack.pushPose();

                    ItemStack canister = component.getCanisterAtIndex(i);

                    float scale = 1/3f;
                    poseStack.rotateAround(new Quaternionf().rotationXYZ(loc.rotation().x, loc.rotation().y, loc.rotation().z), loc.pivot().x, loc.pivot().y, loc.pivot().z);
                    poseStack.translate(loc.pivot().x, loc.pivot().y, loc.pivot().z + (scale / 2));
                    poseStack.mulPose(Axis.XP.rotationDegrees(-90));

                    poseStack.scale(scale, scale, scale);

                    BakedModel canisterModel = mc.getItemRenderer().getModel(canister, mc.level, mc.player, 0);
                    Minecraft.getInstance().getItemRenderer().render(canister, ItemDisplayContext.NONE, false, poseStack, multiBufferSource, packedLight, packedOverlay, canisterModel);

                    poseStack.popPose();
                }
            }
        }
    }

    public static void renderSpecial(CubeModelElement cube, VertexConsumer consumer, Matrix4f matrix4f, int packedLight, RGBA rgba, ResourceLocation location) {

        location = ResourceLocation.fromNamespaceAndPath(location.getNamespace(), "block/flamethrower/" + location.getPath());

        Vec3 start = new Vec3(cube.from());
        Vec3 end = new Vec3(cube.to());
        if (cube.up() != null) {
            RenderUtil.drawPlane(consumer, matrix4f, packedLight, rgba, new Vec3(start.x, end.y, start.z), new Vec3(end.x, end.y, end.z), new Vec3(end.x, end.y, start.z), new Vec3(start.x, end.y, end.z), uv(cube.up(), (TextureAtlasSprite)Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location)).flip());
        }

        if (cube.down() != null) {
            RenderUtil.drawPlane(consumer, matrix4f, packedLight, rgba, new Vec3(start.x, start.y, end.z), new Vec3(end.x, start.y, start.z), new Vec3(end.x, start.y, end.z), new Vec3(start.x, start.y, start.z), uv(cube.down(), (TextureAtlasSprite)Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location)).flip());
        }

        if (cube.north() != null) {
            RenderUtil.drawPlane(consumer, matrix4f, packedLight, rgba, new Vec3(start.x, start.y, start.z), new Vec3(end.x, end.y, start.z), new Vec3(end.x, start.y, start.z), new Vec3(start.x, end.y, start.z), uv(cube.north(), (TextureAtlasSprite)Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location)));
        }

        if (cube.south() != null) {
            RenderUtil.drawPlane(consumer, matrix4f, packedLight, rgba, new Vec3(start.x, end.y, end.z), new Vec3(end.x, start.y, end.z), new Vec3(end.x, end.y, end.z), new Vec3(start.x, start.y, end.z), uv(cube.south(), (TextureAtlasSprite)Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location)).flip());
        }

        if (cube.east() != null) {
            RenderUtil.drawPlane(consumer, matrix4f, packedLight, rgba, new Vec3(end.x, start.y, start.z), new Vec3(end.x, end.y, end.z), new Vec3(end.x, start.y, end.z), new Vec3(end.x, end.y, start.z), uv(cube.east(), (TextureAtlasSprite)Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location)));
        }

        if (cube.west() != null) {
            RenderUtil.drawPlane(consumer, matrix4f, packedLight, rgba, new Vec3(start.x, end.y, start.z), new Vec3(start.x, start.y, end.z), new Vec3(start.x, end.y, end.z), new Vec3(start.x, start.y, start.z), uv(cube.west(), (TextureAtlasSprite)Minecraft.getInstance().getTextureAtlas(InventoryMenu.BLOCK_ATLAS).apply(location)).flip());
        }
    }

    private static UV uv(Face face, TextureAtlasSprite sprite) {
        UV uv = face.uv();
        float u = sprite.getU(uv.startU());
        float v = sprite.getV(uv.startV());
        float uu = sprite.getU(uv.endU());
        float vv = sprite.getV(uv.endV());
        return new UV(u, v, uu, vv);
    }
}
