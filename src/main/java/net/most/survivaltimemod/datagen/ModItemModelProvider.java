package net.most.survivaltimemod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.datagen.util.SurvivalTimeUtilGenerator;

import java.util.Map;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SurvivalTimeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        for (RegistryObject<Item> item : SurvivalTimeUtilGenerator.ITEM_REGISTER_MODEL_LIST) {
            simpleItem(item);
        }


        for (RegistryObject<Block> block : SurvivalTimeUtilGenerator.BLOCK_REGISTER_MODEL_LIST) {
            simpleItemBlock(block);
        }

        for (Map.Entry<String, Block> entry : SurvivalTimeUtilGenerator.WITH_MODEL_REGISTER_BLOCK_ENTITY_REGISTER_BLOCK.entrySet()) {
            Block block = entry.getValue();
            complexBlock(block);
        }

//        complexBlock(ModBlocks.HOURGLASS_HUB_STATION.get());


    }

    private ItemModelBuilder complexBlock(Block block) {
        return withExistingParent(ForgeRegistries.BLOCKS.getKey(block).getPath(),
                new ResourceLocation(SurvivalTimeMod.MOD_ID,
                "block/" + ForgeRegistries.BLOCKS.getKey(block).getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SurvivalTimeMod.MOD_ID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItemBlock(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation(SurvivalTimeMod.MOD_ID, "block/" + block.getId().getPath()));
    }
}
