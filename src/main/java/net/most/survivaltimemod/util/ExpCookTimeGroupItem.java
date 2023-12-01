package net.most.survivaltimemod.util;

import net.minecraft.world.level.ItemLike;

public class ExpCookTimeGroupItem {

    private float exp;
    private int cookTime;
    private String group;

    private ItemLike result;

    public ExpCookTimeGroupItem(float exp, int cookTime, String group, ItemLike result) {
        this.exp = exp;
        this.cookTime = cookTime;
        this.group = group;
        this.result = result;
    }

    public float getExp() {
        return exp;
    }


    public int getCookTime() {
        return cookTime;
    }

    public String getGroup() {
        return group;
    }

    public ItemLike getResult() {
        return result;
    }

    public void setExp(float exp) {
        this.exp = exp;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setResult(ItemLike result) {
        this.result = result;
    }

}
