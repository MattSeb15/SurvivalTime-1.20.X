package net.most.survivaltimemod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.custom.TemporalTuberCropBlock;
import net.most.survivaltimemod.data.CropBlockSeedItemModel;
import net.most.survivaltimemod.datagen.util.SurvivalTimeUtilGenerator;

import java.util.Map;
import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, SurvivalTimeMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        for (CropBlockSeedItemModel model : SurvivalTimeUtilGenerator.CROP_REGISTER_MODEL_LIST) {
            makeCrop(model.getBlock(), model.getModelName(), model.getTextureName(), model.getMaxTextures());
        }

        for (RegistryObject<Block> blockRegistryObject : SurvivalTimeUtilGenerator.BLOCK_REGISTER_MODEL_LIST) {
            blockWithItem(blockRegistryObject);
        }
        for (Map.Entry<String, Block> entry : SurvivalTimeUtilGenerator.WITH_MODEL_REGISTER_BLOCK_ENTITY_REGISTER_BLOCK.entrySet()) {
            Block block = entry.getValue();
            String name = entry.getKey();
            horizontalBlock(block, new ModelFile.UncheckedModelFile(modLoc("block/" + name)));
        }

        for (Map.Entry<String, Block> entry : SurvivalTimeUtilGenerator.WITH_MODEL_REGISTER_SIMPLE_BLOCK_WITH_ITEM.entrySet()) {
            Block block = entry.getValue();
            String name = entry.getKey();
            simpleBlockWithItem(block, new ModelFile.UncheckedModelFile(modLoc("block/" + name)));
        }

    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void makeCrop(CropBlock block, String modelName, String textureName, int maxTextures) {
        Function<BlockState, ConfiguredModel[]> function = state -> states(state, block, modelName, textureName,
                maxTextures);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] states(BlockState state, CropBlock block, String modelName, String textureName,
                                     int maxTextures) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        int currentAge = state.getValue(((TemporalTuberCropBlock) block).getAgeProperty());
        int result = getResult(currentAge, maxTextures, block.getMaxAge());
        models[0] =
                new ConfiguredModel(models().crop(modelName + state.getValue(((TemporalTuberCropBlock) block).getAgeProperty()),
                        new ResourceLocation(SurvivalTimeMod.MOD_ID,
                                "block/" + textureName + result)).renderType("cutout"));

        return models;
    }

    private static int getResult(int currentAge, int maxTextures, int maxAge) {
        if (maxTextures == maxAge) {
            return currentAge;
        }
        if (currentAge == maxAge) {
            return maxTextures;
        }
        int result = currentAge * maxTextures / maxAge;
        return Math.min(result, maxTextures);

    }
}
