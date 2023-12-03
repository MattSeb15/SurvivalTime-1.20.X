package net.most.survivaltimemod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.util.ModTags;
import net.most.survivaltimemod.util.SurvivalTimeUtilGenerator;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, SurvivalTimeMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

        for(RegistryObject<Block> block : ModBlocks.BLOCKS.getEntries()) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_STONE_TOOL_LIST) {
            this.tag(BlockTags.NEEDS_STONE_TOOL).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_IRON_TOOL_LIST) {
            this.tag(BlockTags.NEEDS_IRON_TOOL).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_DIAMOND_TOOL_LIST) {
            this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_NETHERITE_TOOL_LIST) {
            this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL).add(block.get());
        }


        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_OPAL_TOOL_LIST) {
            this.tag(ModTags.Blocks.NEEDS_OPAL_TOOL).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_CHRONA_TOOL_LIST) {
            this.tag(ModTags.Blocks.NEEDS_CHRONA_TOOL).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_TEMPORA_TOOL_LIST) {
            this.tag(ModTags.Blocks.NEEDS_TEMPORA_TOOL).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_EPOCH_TOOL_LIST) {
            this.tag(ModTags.Blocks.NEEDS_EPOCH_TOOL).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_FLUX_TOOL_LIST) {
            this.tag(ModTags.Blocks.NEEDS_FLUX_TOOL).add(block.get());
        }

        for(RegistryObject<Block> block : SurvivalTimeUtilGenerator.NEEDS_LOOP_TOOL_LIST) {
            this.tag(ModTags.Blocks.NEEDS_LOOP_TOOL).add(block.get());
        }


    }
}
