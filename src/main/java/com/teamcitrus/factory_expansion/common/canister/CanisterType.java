package com.teamcitrus.factory_expansion.common.canister;

import com.mojang.serialization.Codec;
import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import com.teamcitrus.factory_expansion.core.FERegistries;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class CanisterType {

    public static final Codec<CanisterType> CODEC = FERegistries.CANISTER_TYPE.byNameCodec();

    protected static final Minecraft minecraft = Minecraft.getInstance();

    public CanisterType() {}

    public Component getName() {
        return Component.translatable(Util.makeDescriptionId("canister", FERegistries.CANISTER_TYPE.getKey(this)));
    };

    /// runs on the flamethrower's tick
    public abstract void process(ItemStack flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector);
}
