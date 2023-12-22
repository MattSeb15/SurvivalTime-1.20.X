package net.most.survivaltimemod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.RecipeMatcher;
import net.most.survivaltimemod.SurvivalTimeMod;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HourglassHubStationShapelessRecipe implements Recipe<SimpleContainer> {

    private static final int MAX_WIDTH = 5;
    private static final int MAX_HEIGHT = 5;
    private final ResourceLocation id;
    private final ItemStack resultItem;
    private final NonNullList<Ingredient> ingredients;
    private final boolean isSimple;

    private final int craftTime;
    private final int energyCost;


    public HourglassHubStationShapelessRecipe(ResourceLocation id, ItemStack resultItem, NonNullList<Ingredient> ingredients, int craftTime,
                                              int energyCost) {
        this.ingredients = ingredients;
        this.resultItem = resultItem;
        this.id = id;
        this.isSimple = ingredients.stream().allMatch(Ingredient::isSimple);
        this.craftTime = craftTime;
        this.energyCost = energyCost;
    }

    @Override
    public boolean isSpecial() {
        return true;
    }


    @Override
    public boolean matches(@NotNull SimpleContainer pContainer, @NotNull Level pLevel) {
        StackedContents stackedcontents = new StackedContents();
        List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for (int j = 0; j < 25; ++j) {
            ItemStack itemstack = pContainer.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (isSimple)
                    stackedcontents.accountStack(itemstack, 1);
                else inputs.add(itemstack);
            }
        }

        return i == this.ingredients.size() && (isSimple ? stackedcontents.canCraft(this, (IntList) null) : RecipeMatcher.findMatches(inputs,
                this.ingredients) != null);
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return resultItem.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= this.ingredients.size();
    }

    @Override
    public @NotNull ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return resultItem.copy();
    }

    public int getCraftTime() {
        return craftTime;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return ModRecipes.HOURGLASS_HUB_SHAPELESS_SERIALIZER.get();
    }

    @Override
    public @NotNull RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<HourglassHubStationShapelessRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();

        public static final String ID = "hourglass_hub_shapeless";
    }


    ///serializer--------------------------------------------------------------------------------------------------------------------
    public static class Serializer implements RecipeSerializer<HourglassHubStationShapelessRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(SurvivalTimeMod.MOD_ID, "hourglass_hub_shapeless");

        @Override
        public @NotNull HourglassHubStationShapelessRecipe fromJson(@NotNull ResourceLocation pRecipeId, @NotNull JsonObject pJson) {

            NonNullList<Ingredient> nonnulllist = itemsFromJson(GsonHelper.getAsJsonArray(pJson, "ingredients"));
            int craftTime = GsonHelper.getAsInt(pJson, "craftTime");
            int energyCost = GsonHelper.getAsInt(pJson, "energyCost");
            if (nonnulllist.isEmpty()) {
                throw new JsonParseException("No ingredients for shapeless recipe");
            } else if (nonnulllist.size() > HourglassHubStationShapelessRecipe.MAX_WIDTH * HourglassHubStationShapelessRecipe.MAX_HEIGHT) {
                throw new JsonParseException("Too many ingredients for shapeless recipe. The maximum is " + (HourglassHubStationShapelessRecipe.MAX_WIDTH * HourglassHubStationShapelessRecipe.MAX_HEIGHT));
            } else {
                ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pJson, "result"));
                return new HourglassHubStationShapelessRecipe(pRecipeId, itemstack, nonnulllist, craftTime, energyCost);
            }
        }

        private static NonNullList<Ingredient> itemsFromJson(JsonArray pIngredientArray) {
            NonNullList<Ingredient> nonnulllist = NonNullList.create();

            for (int i = 0; i < pIngredientArray.size(); ++i) {
                Ingredient ingredient = Ingredient.fromJson(pIngredientArray.get(i), false);
                if (true || !ingredient.isEmpty()) { // FORGE: Skip checking if an ingredient is empty during shapeless recipe deserialization to
                    // prevent complex ingredients from caching tags too early. Can not be done using a config value due to sync issues.
                    nonnulllist.add(ingredient);
                }
            }

            return nonnulllist;
        }

        @Override
        public HourglassHubStationShapelessRecipe fromNetwork(@NotNull ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int i = pBuffer.readVarInt();
            NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i, Ingredient.EMPTY);


            for (int j = 0; j < nonnulllist.size(); ++j) {
                nonnulllist.set(j, Ingredient.fromNetwork(pBuffer));
            }

            int craftTime = pBuffer.readVarInt();
            int energyCost = pBuffer.readVarInt();

            ItemStack itemstack = pBuffer.readItem();
            return new HourglassHubStationShapelessRecipe(pRecipeId, itemstack, nonnulllist, craftTime, energyCost);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, HourglassHubStationShapelessRecipe pRecipe) {
            pBuffer.writeVarInt(pRecipe.ingredients.size());

            for (Ingredient ingredient : pRecipe.ingredients) {
                ingredient.toNetwork(pBuffer);
            }

            pBuffer.writeVarInt(pRecipe.craftTime);
            pBuffer.writeVarInt(pRecipe.energyCost);

            pBuffer.writeItemStack(pRecipe.resultItem, false);
        }
    }


}
