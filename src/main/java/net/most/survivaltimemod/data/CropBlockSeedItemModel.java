package net.most.survivaltimemod.data;

import net.minecraft.world.level.block.CropBlock;

public class CropBlockSeedItemModel {
    private CropBlock block;
    private final String modelName;
    private final String textureName;
    private final int maxTextures;

    public CropBlockSeedItemModel(CropBlock block, String modelName, String textureName, int maxTextures) {
        this.block = block;
        this.modelName = modelName;
        this.textureName = textureName;
        this.maxTextures = maxTextures;
    }

    public CropBlock getBlock() {
        return block;
    }

    public String getModelName() {
        return modelName;
    }

    public String getTextureName() {
        return textureName;
    }

    public int getMaxTextures() {
        return maxTextures;
    }

    public void setBlock(CropBlock block) {
        this.block = block;
    }
}
