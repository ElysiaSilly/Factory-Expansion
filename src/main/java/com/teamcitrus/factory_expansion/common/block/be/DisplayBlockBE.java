package com.teamcitrus.factory_expansion.common.block.be;

import com.teamcitrus.factory_expansion.common.block.DisplayBlock;
import com.teamcitrus.factory_expansion.core.registry.FEBlockEntities;
import com.teamcitrus.factory_expansion.core.util.ColourUtils;
import com.teamcitrus.factory_expansion.core.util.RGBA;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class DisplayBlockBE extends BlockEntity {

    private String string = " ";
    private int colour = ColourUtils.RGBAToDec(new RGBA(242, 189, 116, 0));
    private int seededColour = 10;
    private float seed = 10;
    private String font = "default";

    public DisplayBlockBE(BlockPos pos, BlockState blockState) {
        super(FEBlockEntities.DISPLAY_BE.get(), pos, blockState);
    }

    @Override
    public void setLevel(Level level) {
        super.setLevel(level);

        if(this.seed == 10 && !level.isClientSide) seed(level);
        if(this.seededColour == 10 && !level.isClientSide) setColour(this.colour);
    }

    public void setColour(int colour) {
        this.colour = colour;
        setSeededColour(this.colour);
    }

    public void setCharacter(char character) {
        this.string = String.valueOf(character);
        markUpdated();
    }

    public String getString() {
        return this.string;
    }

    public void seed(Level level) {
        int ran = level.random.nextIntBetweenInclusive(1, 7);
        this.seed = ran > 3 ? (float) (ran * 0.25) : 1;
        setColour(this.colour);
        markUpdated();
    }

    public void setSeededColour(int colour) {
        RGBA c = ColourUtils.DecToRGBA(colour).shade(getSeed());
        this.seededColour = ColourUtils.RGBAToDec(c);
        markUpdated();
    }

    public int getColour() {
        return this.colour;
    }

    public void setFont(String font) {
        this.font = font;
        markUpdated();
    }

    public String getFont() {
        return this.font;
    }

    public int getSeededColour() {
        return this.seededColour;
    }

    public float getSeed() {
        return this.seed;
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private void markUpdated() {
        this.setChanged();
        assert level != null;
        level.sendBlockUpdated(worldPosition, this.getBlockState(), this.getBlockState(), DisplayBlock.UPDATE_CLIENTS);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();

        //tag.putString("string", this.string);
        //tag.putInt("colour", this.colour);
        //tag.putFloat("seed", this.seed);

        return saveData(tag);
    }

    @Override
    public void handleUpdateTag(CompoundTag tag, HolderLookup.Provider lookupProvider) {
        super.handleUpdateTag(tag, lookupProvider);

        loadData(tag);

        //this.colour = tag.getInt("colour");
        //this.string = tag.getString("string");
        //this.seed = tag.getFloat("seed");
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);

        saveData(tag);

        //tag.putInt("colour", this.colour);
        //tag.putString("string", this.string);
        //tag.putFloat("seed", this.seed);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);

        loadData(tag);

        //this.string = tag.getString("string");
        //this.colour = tag.getInt("colour");
        //this.seed = tag.getFloat("seed");
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt, HolderLookup.Provider lookupProvider) {

        loadData(pkt.getTag());

        //this.string = pkt.getTag().getString("string");
        //this.colour = pkt.getTag().getInt("colour");
        //this.seed = pkt.getTag().getFloat("seed");
    }

    public CompoundTag saveData(CompoundTag tag) {

        tag.putInt("seededColour", this.seededColour);
        tag.putInt("colour", this.colour);
        tag.putString("string", this.string);
        tag.putFloat("seed", this.seed);
        tag.putString("font", this.font);

        return tag;
    }

    public CompoundTag loadData(CompoundTag tag) {

        this.seededColour = tag.getInt("seededColour");
        this.string = tag.getString("string");
        this.colour = tag.getInt("colour");
        this.seed = tag.getFloat("seed");
        this.font = tag.getString("font");

        return tag;
    }
}
