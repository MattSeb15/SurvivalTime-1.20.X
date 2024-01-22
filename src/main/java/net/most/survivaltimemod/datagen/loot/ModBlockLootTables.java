package net.most.survivaltimemod.datagen.loot;

import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.AlternativesEntry;
import net.minecraft.world.level.storage.loot.entries.EntryGroup;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.data.CropBlockSeedItem;
import net.most.survivaltimemod.item.ModItems;
import net.most.survivaltimemod.util.ShardOptions;
import net.most.survivaltimemod.datagen.util.SurvivalTimeUtilGenerator;
import org.jetbrains.annotations.NotNull;

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
        for (Map.Entry<RegistryObject<Block>, ShardOptions> entry :
                SurvivalTimeUtilGenerator.OPAL_ORE_LOOT_TABLE_MAP.entrySet()) {
            RegistryObject<Block> blockRegistryObject = entry.getKey();
            ShardOptions shardOptions = entry.getValue();
            this.add(blockRegistryObject.get(), block -> createOpalOreDrops(block, shardOptions));

        }

        for (CropBlockSeedItem cropBlockSeedItem : SurvivalTimeUtilGenerator.CROP_BLOCK_SEED_ITEM_LIST) {
            this.add(cropBlockSeedItem.getCropBlock(), this.createCustomCropDrop(cropBlockSeedItem));
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

    protected LootTable.Builder createCustomCropDrop(CropBlockSeedItem pCropBlockSeedItem) {

        Block pCropBlock = pCropBlockSeedItem.getCropBlock();
        Item pGrownCropItem = pCropBlockSeedItem.getResultItem();
        Item pRottenCropItem = pCropBlockSeedItem.getRottenItem();
        Item pSeedsItem = pCropBlockSeedItem.getSeeds();
        IntegerProperty pAgeProperty = pCropBlockSeedItem.getAgeProperty();
        int pMaxAge = pCropBlockSeedItem.getMaxAge();

        LootItemCondition.Builder pDropGrownCropConditionRotten =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(pCropBlock)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(pAgeProperty,
                                pMaxAge));
        LootItemCondition.Builder pDropGrownCropCondition =
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(pCropBlock)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(pAgeProperty,
                                pMaxAge - 1));

        return this.applyExplosionDecay(pCropBlock,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .add(LootItem.lootTableItem(pRottenCropItem)
                                                .when(pDropGrownCropConditionRotten)
                                                .when(LootItemRandomChanceCondition.randomChance(0.3f))
                                                .otherwise(
                                                        LootItem.lootTableItem(pGrownCropItem)
                                                                .when(pDropGrownCropCondition)
                                                ).otherwise(LootItem.lootTableItem(pSeedsItem).when(
                                                        LootItemRandomChanceCondition.randomChance(0.05f)
                                                ))
                                        )
                        ).withPool(
                                LootPool.lootPool()
                                        .when(pDropGrownCropCondition)
                                        .add(LootItem.lootTableItem(pSeedsItem)
                                                .apply(ApplyBonusCount
                                                        .addBonusBinomialDistributionCount(
                                                                Enchantments.BLOCK_FORTUNE,
                                                                0.5714286F,
                                                                1
                                                        )
                                                )
                                        )
                        )
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
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
