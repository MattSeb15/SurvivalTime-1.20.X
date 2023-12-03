package net.most.survivaltimemod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.most.survivaltimemod.SurvivalTimeMod;

public class ModTags {
    public static class Blocks {

        //needs shards tool
        public static final TagKey<Block> NEEDS_OPAL_TOOL = tag("needs_opal_tool");
        public static final TagKey<Block> NEEDS_CHRONA_TOOL = tag("needs_chrona_tool");
        public static final TagKey<Block> NEEDS_TEMPORA_TOOL = tag("needs_tempora_tool");
        public static final TagKey<Block> NEEDS_EPOCH_TOOL = tag("needs_epoch_tool");
        public static final TagKey<Block> NEEDS_FLUX_TOOL = tag("needs_flux_tool");
        public static final TagKey<Block> NEEDS_LOOP_TOOL = tag("needs_loop_tool");



        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(SurvivalTimeMod.MOD_ID, name));
        }
    }

    public static class Items {

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(SurvivalTimeMod.MOD_ID, name));
        }
    }
}
