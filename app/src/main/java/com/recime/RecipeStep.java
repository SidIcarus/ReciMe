package com.recime;

import com.orm.SugarRecord;

public class RecipeStep extends SugarRecord<RecipeStep> {
    Recipe recipe;
    Integer stepNum;
    String action;
    Float stepTime;

    public RecipeStep(){
    }

    public RecipeStep(Recipe recipe, Integer stepNum, String action, Float stepTime){
        this.recipe = recipe;
        this.stepNum = stepNum;
        this.action = action;
        this.stepTime = stepTime;
    }
}
