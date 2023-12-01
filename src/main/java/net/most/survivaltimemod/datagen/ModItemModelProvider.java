package net.most.survivaltimemod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.util.SurvivalTimeUtilGenerator;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, SurvivalTimeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

        for(RegistryObject<Item> item : SurvivalTimeUtilGenerator.ITEM_LIST) {
            simpleItem(item);
        }


        for(RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
            simpleItemBlock(block);
        }


    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(SurvivalTimeMod.MOD_ID,"item/" + item.getId().getPath()));
    }
    private ItemModelBuilder simpleItemBlock(RegistryObject<Block> block) {
        return withExistingParent(block.getId().getPath(),
                new ResourceLocation(SurvivalTimeMod.MOD_ID,"block/" + block.getId().getPath()));
    }
}
