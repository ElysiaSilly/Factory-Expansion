package com.elysiasilly.fne.common.canister;

import com.elysiasilly.babel.util.MCUtil;
import com.elysiasilly.babel.util.MathUtil;
import com.elysiasilly.babel.util.conversions.VectorConversions;
import com.elysiasilly.fne.core.FERegistries;
import net.minecraft.Util;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Vector3d;

import java.util.ArrayList;
import java.util.List;

public abstract class Canister {

    public final void process(ItemStack flamethrower, ItemStack canister, Level level, Player player) {
        List<Vector3d> ray = slightlyLessShittyRaycast(player, range(flamethrower, canister, level, player));

        tick(flamethrower, canister, level, player, ray);

        List<ItemEntity> items = new ArrayList<>();

        for(Vector3d vec : ray) {
            AABB area = new AABB(vec(vec), vec(vec)).inflate(inflate(flamethrower, canister, level, player));
            for(Entity entity : level.getEntities(null, area)) {
                if(entity instanceof ItemEntity item) {
                    items.add(item);
                } else {
                    entityBurn(flamethrower, canister, level, player, entity);
                }
            }
            for(BlockState state : level.getBlockStates(area).toList()) {
                if(!state.isAir()) blockBurn(flamethrower, canister, level, player, state, BlockPos.containing(vec(vec)));
            }
        }

        if(!items.isEmpty()) itemBurn(flamethrower, canister, level, player, items);
    }

    ///

    public abstract void tick(ItemStack flamethrower, ItemStack canister, Level level, Player player, List<Vector3d> raycast);

    public abstract void blockBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, BlockState state, BlockPos pos);

    public abstract void entityBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, Entity entity);

    public abstract void itemBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, List<ItemEntity> items);

    ///

    public double range(ItemStack flamethrower, ItemStack canister, Level level, Player player) {
        return player.blockInteractionRange();
    }

    public float inflate(ItemStack flamethrower, ItemStack canister, Level level, Player player) {
        return .5f;
    }

    ///

    public Component translationKey() {
        return Component.translatable(Util.makeDescriptionId("canister", key()));
    }

    public ResourceLocation key() {
        return FERegistries.CANISTER_TYPE.getKey(this);
    }

    /// temp

    public static List<Vector3d> slightlyLessShittyRaycast(Player player, double range) {
        return slightlyLessShittyRaycast(vec(player.getEyePosition()), vec(player.getLookAngle()), range, (int) Math.ceil(range * 200), player.level());
    }

    public static Vector3d vec(Vec3 vec) {
        return new Vector3d(vec.x, vec.y, vec.z);
    }

    public static Vec3 vec(Vector3d vec) {
        return new Vec3(vec.x, vec.y, vec.z);
    }

    public static List<Vector3d> slightlyLessShittyRaycast(Vector3d position, Vector3d direction, double distance, int steps, Level level) {
        Vector3d endPos = offset(position, direction, distance);

        List<Vector3d> ray = new ArrayList<>();

        for(int step = 0; step <= steps; step++) {
            Vector3d temp = new Vector3d(position);
            temp.lerp(endPos, (double) step / steps);
            if(level.getBlockState(VectorConversions.toBlockPos(temp)).isSolid()) break; // todo
            ray.add(temp);
        }

        return ray;
    }

    public static Vector3d offset(Vector3d position, Vector3d direction, double distance) {
        return position.add(direction.mul(new Vector3d(distance)));
    }
}
