package com.elysiasilly.fne.common.component;

import com.elysiasilly.babel.util.resource.RGBA;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;

import java.util.UUID;

public record SkinTokenComponent(UUID player, ResourceLocation skin, RGBA rgba) {

    public SkinTokenComponent(String uuid, ResourceLocation skin, RGBA rgba) {
        this(UUID.fromString(uuid), skin, rgba);
    }

    public static final Codec<SkinTokenComponent> CODEC = RecordCodecBuilder.create(builder -> builder.group(
            UUIDUtil.CODEC.fieldOf("player").forGetter(SkinTokenComponent::player),
            ResourceLocation.CODEC.fieldOf("skin").forGetter(SkinTokenComponent::skin),
            RGBA.CODEC.fieldOf("rgba").forGetter(SkinTokenComponent::rgba)
    ).apply(builder, SkinTokenComponent::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, SkinTokenComponent> STREAM = StreamCodec.composite(
            UUIDUtil.STREAM_CODEC, SkinTokenComponent::player,
            ResourceLocation.STREAM_CODEC, SkinTokenComponent::skin,
            RGBA.STREAM_CODEC, SkinTokenComponent::rgba,
            SkinTokenComponent::new
    );

    public Component translationKey() {
        return Component.translatable("flamethrower_skin." + skin().getNamespace() + "." + skin().getPath());
    }
}
