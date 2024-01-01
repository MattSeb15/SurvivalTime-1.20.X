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
import net.minecraft.world.item.crafting.Ingredient;
import net.most.survivaltimemod.SurvivalTimeMod;
import net.most.survivaltimemod.block.ModBlocks;
import net.most.survivaltimemod.recipe.HourglassHubStationShapedRecipe;
import net.most.survivaltimemod.recipe.HourglassHubStationShapelessRecipe;
import net.most.survivaltimemod.screen.renderer.EnergyDisplayTooltipArea;
import net.most.survivaltimemod.util.MouseUtil;
import net.most.survivaltimemod.world.inventory.HourglassHubStationMenu;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HourglassHubStationShapedRecipeCategory implements IRecipeCategory<HourglassHubStationShapedRecipe> {

    public static final ResourceLocation ID = new ResourceLocation(SurvivalTimeMod.MOD_ID, "hourglass_hub_station_shaped_recipe_category");
    public static final ResourceLocation TEXTURE = new ResourceLocation(SurvivalTimeMod.MOD_ID, "textures/gui/hourglass_hub_station.png");

    public static final RecipeType<HourglassHubStationShapedRecipe> RECIPE_TYPE = new RecipeType<>(ID,
            HourglassHubStationShapedRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public HourglassHubStationShapedRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 0, 0, 176, 115);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.HOURGLASS_HUB_STATION.get()));
    }

    @Override
    public @NotNull RecipeType<HourglassHubStationShapedRecipe> getRecipeType() {
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
    public void draw(@NotNull HourglassHubStationShapedRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
                     @NotNull GuiGraphics guiGraphics, double mouseX, double mouseY) {
        IRecipeCategory.super.draw(recipe, recipeSlotsView, guiGraphics, mouseX, mouseY);
        int energyCost = recipe.getEnergyCost();
        int maxEnergyCost = 3600 * 10;
        int xPos = 13;
        int yPos = 11;
        int width = 9;
        int height = 88;
        if (energyCost > 0) {
            EnergyDisplayTooltipArea.render(guiGraphics, energyCost, maxEnergyCost, xPos, yPos, width, height);
        }
    }

    @Override
    public @NotNull List<Component> getTooltipStrings(@NotNull HourglassHubStationShapedRecipe recipe, @NotNull IRecipeSlotsView recipeSlotsView,
                                                      double mouseX, double mouseY) {
        if (MouseUtil.isMouseOver(mouseX, mouseY, 12, 11, 9, 88) && recipe.getEnergyCost() > 0) {
            return EnergyDisplayTooltipArea.getTooltips(recipe.getEnergyCost());

        }
        return IRecipeCategory.super.getTooltipStrings(recipe, recipeSlotsView, mouseX, mouseY);
    }

    @Override
    public void setRecipe(@NotNull IRecipeLayoutBuilder builder, @NotNull HourglassHubStationShapedRecipe recipe, @NotNull IFocusGroup focuses) {


        int initialPositionGridX = HourglassHubStationMenu.initialPositionGridX;
        int initialPositionGridY = HourglassHubStationMenu.initialPositionGridY + 18;
        int gridIncrement = HourglassHubStationMenu.gridIncrement;
        //grid is 5x5
        //index 0-24
//        recipe.getIngredients().forEach(ingredient -> {
//            if(ingredient == Ingredient.EMPTY)
//                return;
//            builder.addSlot(RecipeIngredientRole.INPUT,
//                    initialPositionGridX + (recipe.getIngredients().indexOf(ingredient) % 5) * gridIncrement,
//                    initialPositionGridY + (recipe.getIngredients().indexOf(ingredient) / 5) * gridIncrement).addIngredients(ingredient);
//        });
         System.out.println("###### RECIPE SIZE::::::: "+ recipe.getIngredients().size());

//
//         for(int i = 0; i < recipe.getIngredients().size(); i++) {
//        	 Ingredient ingredient = recipe.getIngredients().get(i);
//        	 if(ingredient == Ingredient.EMPTY)
//        		 continue;
//        	 builder.addSlot(RecipeIngredientRole.INPUT,
//                     initialPositionGridX + (i % 5) * gridIncrement,
//                     initialPositionGridY + (i / 5) * gridIncrement).addIngredients(ingredient);
//         }


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                Ingredient ingredient = recipe.getIngredients().get(i * 5 + j);
                builder.addSlot(RecipeIngredientRole.INPUT,
                        initialPositionGridX + j * gridIncrement,
                        initialPositionGridY + i * gridIncrement).addIngredients(ingredient);
            }
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT,
                HourglassHubStationMenu.outputX,
                HourglassHubStationMenu.outputY + 18
        ).addItemStack(recipe.getResultItem(null));


    }
}
