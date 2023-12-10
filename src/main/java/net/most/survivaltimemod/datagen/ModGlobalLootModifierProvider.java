package net.most.survivaltimemod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.loot.AddItemModifier;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output) {
        super(output, SurvivalTimeMod.MOD_ID);
    }

    @Override
    protected void start() {

        //TODO: HACER QUE LA HIERVA AL DARLE CON ALGO SE CONVIERTA EN UNA HIERVA ESPECIAL Y ESTA SUELTE SEMILLAS DE TEMPORAL TUBER
//        add("kohlrabi_seeds_from_grass", new AddItemModifier(new LootItemCondition[] {
//                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.GRASS).build(),
//                LootItemRandomChanceCondition.randomChance(0.18f).build() }, ModItems.TEMPORAL_TUBER_SEEDS.get()));
//        add("kohlrabi_seeds_from_fern", new AddItemModifier(new LootItemCondition[] {
//                LootItemBlockStatePropertyCondition.hasBlockStateProperties(Blocks.FERN).build(),
//                LootItemRandomChanceCondition.randomChance(0.35f).build() }, ModItems.KOHLRABI_SEEDS.get()));

//        add("metal_detector_from_jungle_temple", new AddItemModifier(new LootItemCondition[] {
//                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_temple")).build() },
//                ModItems.METAL_DETECTOR.get()));

    }
}
