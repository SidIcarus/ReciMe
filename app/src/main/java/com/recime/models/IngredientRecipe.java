package com.recime.models;

import com.orm.SugarRecord;

public class IngredientRecipe extends SugarRecord<IngredientRecipe> {
    Recipe recipe;
    Ingredient ingredient;

    public IngredientRecipe() {}

    public IngredientRecipe(Ingredient ingredient, Recipe recipe) {
        this.recipe = recipe;
        this.ingredient = ingredient;
    }
}
