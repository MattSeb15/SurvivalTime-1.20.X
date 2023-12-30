package net.most.survivaltimemod.compat;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.recipe.HourglassHubStationShapedRecipe;
import net.most.survivaltimemod.recipe.HourglassHubStationShapelessRecipe;
import net.most.survivaltimemod.screen.renderer.EnergyDisplayTooltipArea;
import net.most.survivaltimemod.world.inventory.HourglassHubStationMenu;
import org.jetbrains.annotations.NotNull;

public class HourglassHubStationShapelessRecipeCategory implements IRecipeCategory<HourglassHubStationShapelessRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(SurvivalTimeMod.MOD_ID, "hourglass_hub_station_shapeless_recipe_category");
    public static final ResourceLocation TEXTURE = new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/gui/hourglass_hub_station.png");

    public static final RecipeType<HourglassHubStationShapelessRecipe> RECIPE_TYPE = new RecipeType<>(ID,
            HourglassHubStationShapelessRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public HourglassHubStationShapelessRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 115);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.HOURGLASS_HUB_STATION.get()));
    }

    @Override
    public void draw(@NotNull HourglassHubStationShapelessRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
                     @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        int energyCost = recipe.getEnergyCost();
        int maxEnergyCost = 3600 * 10;
        int xPos = 13;
        int yPos = 29;
        int width = 9;
        int height = 88;
        if (energyCost > 0) {
            EnergyDisplayTooltipArea.render(guiGraphics, energyCost, maxEnergyCost, xPos, yPos, width, height);
        }
    }

    @Override
    public @NotNull RecipeType<HourglassHubStationShapelessRecipe> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.survivaltimemod.hourglass_hub_station");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }


    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull HourglassHubStationShapelessRecipe recipe, @NotNull IFocusGroup focuses) {


        int initialPositionGridX = HourglassHubStationMenu.initialPositionGridX;
        int initialPositionGridY = HourglassHubStationMenu.initialPositionGridY + 18;
        int gridIncrement = HourglassHubStationMenu.gridIncrement;
        //grid is 5x5
        //index 0-24
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                builder.addSlot(RecipeIngredientRole.INPUT,
                        initialPositionGridX + j * gridIncrement,
                        initialPositionGridY + i * gridIncrement).addIngredients(recipe.getIngredients().get(i * 5 + j));
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT,
                HourglassHubStationMenu.outputX,
                HourglassHubStationMenu.outputY + 18
        ).addItemStack(recipe.getResultItem(null));


    }
}
