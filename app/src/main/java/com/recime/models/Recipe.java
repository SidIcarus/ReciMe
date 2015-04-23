package com.recime.models;

import com.orm.SugarRecord;

import java.util.List;

public class Recipe extends SugarRecord<Recipe> {
    String name;

    public Recipe(){
    }

    public Recipe(String name){
        this.name = name;
    }

    public String getName() { return name; }

    public List<RecipeStep> getRecipeSteps() {
        return RecipeStep.findWithQuery(RecipeStep.class, "select * from Recipe_step where recipe = ?", this.getId().toString());
    }

    public List<Ingredient> getIngredients() {
        return Ingredient.findWithQuery(Ingredient.class,
                "select * " +
                        "from ingredient_recipe B " +
                        "inner join ingredient A " +
                        "on A.id = B.ingredient " +
                        "where B.recipe = ?", this.getId().toString());
    }

    public void addIngredient(Ingredient ingredient) {
        IngredientRecipe ingredientRecipe = new IngredientRecipe(ingredient, this);
        ingredientRecipe.save();
    }
}
