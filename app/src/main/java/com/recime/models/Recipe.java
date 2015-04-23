package com.recime;

import com.orm.SugarRecord;

import java.util.List;

public class Recipe extends SugarRecord<Recipe> {
    String name;

    public Recipe(){
    }

    public Recipe(String name){
        this.name = name;
    }

    public List<RecipeStep> getRecipeSteps() {
        return RecipeStep.findWithQuery(RecipeStep.class, "select * from Recipe_step where recipe = ?", this.getId().toString());
    }
}
