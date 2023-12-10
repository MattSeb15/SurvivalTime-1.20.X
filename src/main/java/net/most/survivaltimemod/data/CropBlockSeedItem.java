package net.most.survivaltimemod.data;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class CropBlockSeedItem {
    private final Block cropBlock;
    private final Item seeds;
    private final Item rottenItem;
    private final Item resultItem;
    private final IntegerProperty ageProperty;

    private final int maxAge;


    public CropBlockSeedItem(Block cropBlock, Item seeds, Item rottenItem, Item result, IntegerProperty ageProperty,
                             int maxAge) {
        this.cropBlock = cropBlock;
        this.seeds = seeds;
        this.rottenItem = rottenItem;
        this.resultItem = result;
        this.ageProperty = ageProperty;
        this.maxAge = maxAge;
    }

    public Block getCropBlock() {
        return cropBlock;
    }

    public Item getSeeds() {
        return seeds;
    }

    public Item getRottenItem() {
        return rottenItem;
    }

    public Item getResultItem() {
        return resultItem;
    }

    public IntegerProperty getAgeProperty() {
        return ageProperty;
    }

    public int getMaxAge() {
        return maxAge;
    }


}
