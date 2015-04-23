package com.recime.models;

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

    public Recipe getRecipe() { return recipe; }

    public Integer getStepNum() { return stepNum; }

    public String getAction() { return action; }

    public Float getStepTime() { return stepTime; }
}
