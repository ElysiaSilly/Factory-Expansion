package com.elysiasilly.fne.common.canister;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3d;

import java.util.List;

public class NitroCanister extends Canister {


    @Override
    public void tick(ItemStack flamethrower, ItemStack canister, Level level, Player player, List<Vector3d> raycast) {
        if(!level.isClientSide()) {
            level.explode(null, player.position().x, player.position().y + 1, player.position().z, 10, Level.ExplosionInteraction.TNT);
        }
    }

    @Override
    public void blockBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, BlockState state, BlockPos pos) {
    }

    @Override
    public void entityBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, Entity entity) {

    }

    @Override
    public void itemBurn(ItemStack flamethrower, ItemStack canister, Level level, Player player, List<ItemEntity> items) {

    }

    @Override
    public double range(ItemStack flamethrower, ItemStack canister, Level level, Player player) {
        return 0;
    }
}
