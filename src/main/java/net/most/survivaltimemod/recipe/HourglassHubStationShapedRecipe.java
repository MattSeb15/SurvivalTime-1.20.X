package net.most.survivaltimemod.recipe;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.most.survivaltimemod.SurvivalTimeMod;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.Set;

public class HourglassHubStationShapedRecipe implements Recipe<CraftingContainer> {

    private static final int MAX_WIDTH = 5;
    private static final int MAX_HEIGHT = 5;

    private final int width;
    private final int height;
    private final ResourceLocation id;
    private final ItemStack resultItem;
    private final NonNullList<Ingredient> recipeItems;
    private final int craftTime;
    private final int energyCost;

    public HourglassHubStationShapedRecipe(ResourceLocation id, int width, int height, ItemStack resultItem,
                                           NonNullList<Ingredient> recipeItems, int craftTime, int energyCost) {
        this.width = width;
        this.height = height;
        this.recipeItems = recipeItems;
        this.resultItem = resultItem;
        this.id = id;
        this.craftTime = craftTime;
        this.energyCost = energyCost;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    public int getCraftTime() {
        return craftTime;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    @Override
    public boolean matches(CraftingContainer pInv, @NotNull Level pLevel) {
        for (int i = 0; i <= pInv.getWidth() - this.width; ++i) {
            for (int j = 0; j <= pInv.getHeight() - this.height; ++j) {
                if (this.matches(pInv, i, j, true)) {
                    return true;
                }

                if (this.matches(pInv, i, j, false)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean matches(CraftingContainer pCraftingInventory, int pWidth, int pHeight, boolean pMirrored) {
        for (int i = 0; i < pCraftingInventory.getWidth(); ++i) {
            for (int j = 0; j < pCraftingInventory.getHeight(); ++j) {
                int k = i - pWidth;
                int l = j - pHeight;
                Ingredient ingredient = Ingredient.EMPTY;
                if (k >= 0 && l >= 0 && k < this.width && l < this.height) {
                    if (pMirrored) {
                        ingredient = this.recipeItems.get(this.width - k - 1 + l * this.width);
                    } else {
                        ingredient = this.recipeItems.get(k + l * this.width);
                    }
                }

                if (!ingredient.test(pCraftingInventory.getItem(i + j * pCraftingInventory.getWidth()))) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingContainer pContainer, @NotNull RegistryAccess pRegistryAccess) {

        return this.getResultItem(pRegistryAccess).copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth >= this.width && pHeight >= this.height;
    }

    static NonNullList<Ingredient> dissolvePattern(String[] pPattern, Map<String, Ingredient> pKeys, int pPatternWidth, int pPatternHeight) {
        NonNullList<Ingredient> nonnulllist = NonNullList.withSize(pPatternWidth * pPatternHeight, Ingredient.EMPTY);
        Set<String> set = Sets.newHashSet(pKeys.keySet());
        set.remove(" ");

        for (int i = 0; i < pPattern.length; ++i) {
            for (int j = 0; j < pPattern[i].length(); ++j) {
                String s = pPattern[i].substring(j, j + 1);
                Ingredient ingredient = pKeys.get(s);
                if (ingredient == null) {
                    throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
                }

                set.remove(s);
                nonnulllist.set(j + pPatternWidth * i, ingredient);
            }
        }

        if (!set.isEmpty()) {
            throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
        } else {
            return nonnulllist;
        }
    }

    static String[] patternFromJson(JsonArray pPatternArray) {
        String[] astring = new String[pPatternArray.size()];
        if (astring.length > MAX_HEIGHT) {
            throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
        } else if (astring.length == 0) {
            throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
        } else {
            for (int i = 0; i < astring.length; ++i) {
                String s = GsonHelper.convertToString(pPatternArray.get(i), "pattern[" + i + "]");
                if (s.length() > MAX_WIDTH) {
                    throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
                }

                if (i > 0 && astring[0].length() != s.length()) {
                    throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
                }

                astring[i] = s;
            }

            return astring;
        }
    }

    static Map<String, Ingredient> keyFromJson(JsonObject pKeyEntry) {
        Map<String, Ingredient> map = Maps.newHashMap();

        for (Map.Entry<String, JsonElement> entry : pKeyEntry.entrySet()) {
            if (entry.getKey().length() != 1) {
                throw new JsonSyntaxException("Invalid key entry: '" + (String) entry.getKey() + "' is an invalid symbol (must be 1 character only)" +
                        ".");
            }

            if (" ".equals(entry.getKey())) {
                throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
            }

            map.put(entry.getKey(), Ingredient.fromJson(entry.getValue(), true));
        }

        map.put(" ", Ingredient.EMPTY);
        return map;
    }

    @Override
    public @NotNull ItemStack getResultItem(@NotNull RegistryAccess pRegistryAccess) {
        return resultItem.copy();
    }

    @Override
    public @NotNull NonNullList<Ingredient> getIngredients() {
        return this.recipeItems;
    }

    @Override
    public @NotNull ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.HOURGLASS_HUB_SHAPED_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    static String[] shrink(String... pToShrink) {
        int i = Integer.MAX_VALUE;
        int j = 0;
        int k = 0;
        int l = 0;

        for (int i1 = 0; i1 < pToShrink.length; ++i1) {
            String s = pToShrink[i1];
            i = Math.min(i, firstNonSpace(s));
            int j1 = lastNonSpace(s);
            j = Math.max(j, j1);
            if (j1 < 0) {
                if (k == i1) {
                    ++k;
                }

                ++l;
            } else {
                l = 0;
            }
        }

        if (pToShrink.length == l) {
            return new String[0];
        } else {
            String[] astring = new String[pToShrink.length - l - k];

            for (int k1 = 0; k1 < astring.length; ++k1) {
                astring[k1] = pToShrink[k1 + k].substring(i, j + 1);
            }

            return astring;
        }
    }

    private static int firstNonSpace(String pEntry) {
        int i;
        for (i = 0; i < pEntry.length() && pEntry.charAt(i) == ' '; ++i) {
        }

        return i;
    }

    private static int lastNonSpace(String pEntry) {
        int i;
        for (i = pEntry.length() - 1; i >= 0 && pEntry.charAt(i) == ' '; --i) {
        }

        return i;
    }

    public static class Type implements RecipeType<HourglassHubStationShapedRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();

        public static final String ID = "hourglass_hub_shaped";
    }


    ///serializer--------------------------------------------------------------------------------------------------------------------
    public static class Serializer implements RecipeSerializer<HourglassHubStationShapedRecipe> {

        public static final HourglassHubStationShapedRecipe.Serializer INSTANCE = new HourglassHubStationShapedRecipe.Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(SurvivalTimeMod.MOD_ID, "hourglass_hub_shaped");

        @Override
        public @NotNull HourglassHubStationShapedRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pJson) {

            Map<String, Ingredient> map = HourglassHubStationShapedRecipe.keyFromJson(GsonHelper.getAsJsonObject(pJson, "key"));
            int craftTime = GsonHelper.getAsInt(pJson, "craftTime");
            int energyCost = GsonHelper.getAsInt(pJson, "energyCost");
            String[] astring = HourglassHubStationShapedRecipe.patternFromJson(GsonHelper.getAsJsonArray(pJson,
                    "pattern"));
            int i = astring[0].length();
            int j = astring.length;
            NonNullList<Ingredient> nonnulllist = HourglassHubStationShapedRecipe.dissolvePattern(astring, map, i, j);
            ItemStack itemstack = HourglassHubStationShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));

            return new HourglassHubStationShapedRecipe(pRecipeId, i, j, itemstack, nonnulllist, craftTime, energyCost);
        }

        @Override
        public HourglassHubStationShapedRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int i = pBuffer.readVarInt();
            int j = pBuffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);

            for (int k = 0; k < nonnulllist.size(); ++k) {
                nonnulllist.set(k, Ingredient.fromNetwork(pBuffer));
            }

            int craftTime = pBuffer.readVarInt();
            int energyCost = pBuffer.readVarInt();

            ItemStack itemstack = pBuffer.readItem();

            return new HourglassHubStationShapedRecipe(pRecipeId, i, j, itemstack, nonnulllist, craftTime, energyCost);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, HourglassHubStationShapedRecipe pRecipe) {
            pBuffer.writeVarInt(pRecipe.width);
            pBuffer.writeVarInt(pRecipe.height);


            for (Ingredient ingredient : pRecipe.recipeItems) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeVarInt(pRecipe.craftTime);
            pBuffer.writeVarInt(pRecipe.energyCost);

            pBuffer.writeItem(pRecipe.resultItem);
        }
    }

    private static ItemStack itemStackFromJson(JsonObject result) {
        return CraftingHelper.getItemStack(result, true, true);
    }


}
