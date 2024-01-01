package net.most.survivaltimemod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.client.gui.HourglassHubStationScreen;
import net.most.survivaltimemod.recipe.HourglassHubStationShapedRecipe;
import net.most.survivaltimemod.recipe.HourglassHubStationShapelessRecipe;
import net.most.survivaltimemod.screen.ModMenuTypes;
import net.most.survivaltimemod.world.inventory.HourglassHubStationMenu;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class JEISurvivalTimePlugin implements IModPlugin {
    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return new ResourceLocation(SurvivalTimeMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(@NotNull IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(
                new HourglassHubStationShapedRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(
                new HourglassHubStationShapelessRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();
        List<HourglassHubStationShapedRecipe> shapedRecipes = recipeManager.getAllRecipesFor(HourglassHubStationShapedRecipe.Type.INSTANCE);
        List<HourglassHubStationShapelessRecipe> shapelessRecipes = recipeManager.getAllRecipesFor(HourglassHubStationShapelessRecipe.Type.INSTANCE);
        registration.addRecipes(HourglassHubStationShapedRecipeCategory.RECIPE_TYPE, shapedRecipes);
        registration.addRecipes(HourglassHubStationShapelessRecipeCategory.RECIPE_TYPE, shapelessRecipes);
    }

    @Override
    public void registerGuiHandlers(@NotNull IGuiHandlerRegistration registration) {

        registration.addRecipeClickArea(HourglassHubStationScreen.class, 144, 6 - 18, 10, 10, HourglassHubStationShapedRecipeCategory.RECIPE_TYPE);
        registration.addRecipeClickArea(HourglassHubStationScreen.class, 159, 6 - 18, 10, 10, HourglassHubStationShapelessRecipeCategory.RECIPE_TYPE);

    }

    @Override
    public void registerRecipeTransferHandlers(@NotNull IRecipeTransferRegistration registration) {
        registration.addRecipeTransferHandler(HourglassHubStationMenu.class, ModMenuTypes.HOURGLASS_HUB_STATION_MENU.get(),
                HourglassHubStationShapedRecipeCategory.RECIPE_TYPE, 36, 25, 0, 36);
        registration.addRecipeTransferHandler(HourglassHubStationMenu.class, ModMenuTypes.HOURGLASS_HUB_STATION_MENU.get(),
                HourglassHubStationShapelessRecipeCategory.RECIPE_TYPE, 36, 25, 0, 36);
    }

    @Override
    public void registerRecipeCatalysts(@NotNull IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.HOURGLASS_HUB_STATION.get()), HourglassHubStationShapedRecipeCategory.RECIPE_TYPE);
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.HOURGLASS_HUB_STATION.get()), HourglassHubStationShapelessRecipeCategory.RECIPE_TYPE);
    }
}
