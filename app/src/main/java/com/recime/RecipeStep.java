package com.recime;

import com.orm.SugarRecord;

public class RecipeStep extends SugarRecord<RecipeStep> {
    Recipe recipe;
    Integer stepNum;
    String action;

    public RecipeStep(){
    }

    public RecipeStep(Recipe recipe, Integer stepNum, String action){
        this.recipe = recipe;
        this.stepNum = stepNum;
        this.action = action;
    }
}
