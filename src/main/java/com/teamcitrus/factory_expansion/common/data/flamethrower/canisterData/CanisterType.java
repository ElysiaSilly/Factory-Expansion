package com.teamcitrus.factory_expansion.common.data.flamethrower.canisterData;

import com.teamcitrus.factory_expansion.common.item.FlamethrowerItem;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class CanisterType {

    protected static final Minecraft minecraft = Minecraft.getInstance();

    private final Component name;
    private final ResourceLocation icon;

    // more to come
    public CanisterType(Component name, ResourceLocation icon) {
        this.name = name;
        this.icon = icon;
    }

    public Component getName() {
        return this.name;
    }

    public ResourceLocation getIcon() {
        return this.icon;
    }

    // runs on the flamethrower's tick
    public abstract void process(FlamethrowerItem flamethrower, ItemStack canisterItem, Level level, Player player, Vec3 playerLookVector);
    // put whatever you wanna do when this canister type is being used in the flamethrower here
}
