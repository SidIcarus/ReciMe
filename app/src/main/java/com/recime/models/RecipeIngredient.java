package com.recime.models;

import com.orm.SugarRecord;

import java.util.List;

public class RecipeIngredient extends SugarRecord<RecipeIngredient> {
    public Recipe recipe;
    public Ingredient ingredient;

    public RecipeIngredient() {
    }

    public RecipeIngredient(Recipe recipe, Ingredient ingredient)
    {
        this.recipe = recipe;
        this.ingredient = ingredient;
    }
}
