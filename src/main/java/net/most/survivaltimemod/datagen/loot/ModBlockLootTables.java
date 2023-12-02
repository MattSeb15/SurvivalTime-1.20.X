package net.most.survivaltimemod.datagen.loot;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.EntryGroup;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.util.ShardOptions;
import net.most.survivaltimemod.util.SurvivalTimeUtilGenerator;

import java.util.Map;
import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        for (RegistryObject<Block> block : SurvivalTimeUtilGenerator.DROP_SELF_BLOCKS_LOOT_TABLE_LIST) {
            this.dropSelf(block.get());
        }
        for (Map.Entry<RegistryObject<Block>, ShardOptions> entry : SurvivalTimeUtilGenerator.OPAL_ORE_LOOT_TABLE_MAP.entrySet()) {
            RegistryObject<Block> blockRegistryObject = entry.getKey();
            ShardOptions shardOptions = entry.getValue();
            this.add(blockRegistryObject.get(), block -> createOpalOreDrops(blockRegistryObject.get(), shardOptions));
        }

        this.add(ModBlocks.FIERY_TIME_BLOCK.get(), block -> createFieryTimeBlock(block, ModItems.FIERY_TIME.get()));




    }

    protected LootTable.Builder createOpalOreDrops(Block block, ShardOptions shardOptions) {

        return LootTable.lootTable().withPool(
                LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .setBonusRolls(ConstantValue.exactly(0.0F))
                        .add(AlternativesEntry.alternatives(
                                LootItem.lootTableItem(block).when(MatchTool.toolMatches(
                                        ItemPredicate.Builder.item()
                                                .hasEnchantment(new EnchantmentPredicate(
                                                        Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1)
                                                ))
                                )),
                                EntryGroup.list(
                                        LootItem.lootTableItem(ModItems.OPAL_SHARD_LOOP.get())
                                                .setWeight(shardOptions.getLoopOptions().getWeight())
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(shardOptions.getLoopOptions().getMin(),
                                                                shardOptions.getLoopOptions().getMax()), false
                                                )),
                                        LootItem.lootTableItem(ModItems.OPAL_SHARD_FLUX.get())
                                                .setWeight(shardOptions.getFluxOptions().getWeight())
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(shardOptions.getFluxOptions().getMin(),
                                                                shardOptions.getFluxOptions().getMax()), false
                                                )),
                                        LootItem.lootTableItem(ModItems.OPAL_SHARD_EPOCH.get())
                                                .setWeight(shardOptions.getEpochOptions().getWeight())
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(shardOptions.getEpochOptions().getMin(),
                                                                shardOptions.getEpochOptions().getMax()), false
                                                )),
                                        LootItem.lootTableItem(ModItems.OPAL_SHARD_TEMPORA.get())
                                                .setWeight(shardOptions.getTemporaOptions().getWeight())
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(shardOptions.getTemporaOptions().getMin(),
                                                                shardOptions.getTemporaOptions().getMax()), false
                                                )),
                                        LootItem.lootTableItem(ModItems.OPAL_SHARD_CHRONA.get())
                                                .setWeight(shardOptions.getChronaOptions().getWeight())
                                                .apply(SetItemCountFunction.setCount(
                                                        UniformGenerator.between(shardOptions.getChronaOptions().getMin(),
                                                                shardOptions.getChronaOptions().getMax()), false
                                                ))
                                )

                        ))
        );
    }

    protected LootTable.Builder createFieryTimeBlock(Block block, Item item) {
        return createSilkTouchDispatchTable(block, this.applyExplosionDecay(block,
                LootItem.lootTableItem(item).apply(SetItemCountFunction.setCount(
                        UniformGenerator.between(3.0f, 9.0f), false
                ))
        ));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
